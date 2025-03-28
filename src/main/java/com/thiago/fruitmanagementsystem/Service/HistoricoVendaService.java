package com.thiago.fruitmanagementsystem.Service;

import com.itextpdf.text.DocumentException;
import com.thiago.fruitmanagementsystem.Model.*;
import com.thiago.fruitmanagementsystem.Repository.FrutaRepository;
import com.thiago.fruitmanagementsystem.Repository.HistoricoVendaRepository;
import com.thiago.fruitmanagementsystem.Utils.PdfUtils;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class HistoricoVendaService {

    private final HistoricoVendaRepository historicoVendaRepository;
    private final FrutaRepository frutaRepository;
    private final EmailService emailService;

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Value("${spring.mail.usernameTo}")
    private String emailTo;

    public HistoricoVendaService(HistoricoVendaRepository historicoVendaRepository, com.thiago.fruitmanagementsystem.Repository.FrutaRepository frutaRepository, EmailService emailService) {
        this.historicoVendaRepository = historicoVendaRepository;
        this.frutaRepository = frutaRepository;
        this.emailService = emailService;
    }

    public List<HistoricoResponseDTO> findAllHistoricos() {
        List<HistoricoVendaFrutas> historicoVendas = historicoVendaRepository.findAllHstoricos();
        Map<UUID, HistoricoResponseDTO> historicoMap = new HashMap<>();

        for (HistoricoVendaFrutas historicoVenda : historicoVendas) {
            UUID historicoId = historicoVenda.getHistoricoVendas().getId();
            if (!historicoMap.containsKey(historicoId)) {
                historicoMap.put(historicoId, new HistoricoResponseDTO(
                        historicoId,
                        historicoVenda.getHistoricoVendas().getValorTotal(),
                        new ArrayList<>()
                ));
            }
            HistoricoResponseDTO historicoResponseDTO = historicoMap.get(historicoId);
            historicoResponseDTO.frutasVendidas().add(new FrutaVendaResponseDTO(
                    historicoVenda.getHistoricoVendas().getQtdEscolhida(),
                    historicoVenda.getHistoricoVendas().getDataVenda(),
                    historicoVenda.getFruta()
            ));
        }
        return new ArrayList<>(historicoMap.values());
    }

    public void generatePdf() throws DocumentException, IOException, MessagingException {
        List<HistoricoResponseDTO> historicos = this.findAllHistoricos();
        if (historicos.isEmpty()) {
            System.out.println("No historical sales data found.");
        } else {
            System.out.println("Historical sales data found: " + historicos.size() + " records.");
        }
        byte[] pdfStream = PdfUtils.generatePdfStream(historicos);
        if (pdfStream.length == 0) {
            System.out.println("PDF generation failed or resulted in an empty PDF.");
        } else {
            System.out.println("PDF generated successfully with size: " + pdfStream.length + " bytes.");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=historico_vendas.pdf");
        PdfUtils.savePdfToFile(pdfStream, "historico_vendas.pdf");
        emailService.sendEmailWithPdf(new EmailModelDTO(emailFrom,emailTo,"historico de vendas atualizado","Segue em anexo o historico de vendas desse mes"), pdfStream);
    }




    protected HistoricoVendas createHistoricoVendas(VendaRequestDTO dto) {
        HistoricoVendas historico = new HistoricoVendas();
        historico.setDataVenda(LocalDateTime.now());
        for (FrutasVendasDTO dto2 : dto.frutasVendasDTO()) {
            historico.setQtdEscolhida(dto2.qtdEscolhida());
        }
        return historico;
    }

    protected List<HistoricoVendaFrutas> processFruitsSales(VendaRequestDTO dto, HistoricoVendas historico) {
        List<HistoricoVendaFrutas> historicoVendaFruta = new ArrayList<>();


        Double totalVenda = 0.0;

        for (FrutasVendasDTO dto2 : dto.frutasVendasDTO()) {
            Fruta fruta = frutaRepository.findById(Long.valueOf(dto2.frutaID())).orElseThrow(() -> new RuntimeException("Fruta não encontrada"));
            Double valorVenda = fruta.getValorVenda() * dto2.qtdEscolhida();

            if (dto2.discount() != 0) {
                valorVenda -= (valorVenda * dto2.discount());
            }

            if (dto2.qtdEscolhida() > fruta.getQtdDisponivel()) {
                throw new RuntimeException("Quantidade de fruta Escolhida Maior Do que Quantidade Disponivel");
            }

            fruta.setQtdDisponivel(fruta.getQtdDisponivel() - dto2.qtdEscolhida());
            frutaRepository.save(fruta);


        HistoricoVendaFrutas historicoVendaFrutas = new HistoricoVendaFrutas();
        historicoVendaFrutas.setHistoricoVendas(historico);
        historicoVendaFrutas.setFruta(fruta);
        historicoVendaFruta.add(historicoVendaFrutas);


            totalVenda += valorVenda;
        }

        historico.setValorTotal(totalVenda);
        return historicoVendaFruta;
    }

}
