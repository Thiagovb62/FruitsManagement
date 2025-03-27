package com.thiago.fruitmanagementsystem.Service;

import com.thiago.fruitmanagementsystem.Enums.ClassificacaoEnum;
import com.thiago.fruitmanagementsystem.Model.EmailModelDTO;
import com.thiago.fruitmanagementsystem.Model.Fruta;
import com.thiago.fruitmanagementsystem.Model.FrutasFindBysDTO;
import com.thiago.fruitmanagementsystem.Model.FrutaRequestDTO;
import com.thiago.fruitmanagementsystem.Repository.FrutaRepository;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FrutaService {

    private final FrutaRepository frutaRepository;

    private final EmailService emailService;

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Value("${spring.mail.usernameTo}")
    private String emailTo;

    public FrutaService(FrutaRepository frutaRepository, EmailService emailService) {
        this.frutaRepository = frutaRepository;
        this.emailService = emailService;
    }

    public List<Fruta> findFruitByName(FrutasFindBysDTO dto){
        var name = dto.nome();
        List<Fruta> frutas = frutaRepository.findAllByNome(name);
        if (frutas.isEmpty()){
            throw new EntityNotFoundException("Nenhuma fruta encontrada com o nome " + name + "!");
        }
        return frutas;
    }

    public List<Fruta> getAllFruits(){
        return frutaRepository.findAll();
    }

    public List<Fruta> findAllByClassificacaoOrFrescaAndOrderByValorVendaIdAsc(FrutasFindBysDTO dto) {
        List<Fruta> frutas = frutaRepository.findAllByClassificacaoAndFrescaAndOrderByValorVendaIdAsc(ClassificacaoEnum.fromValor(dto.classificacao()), dto.fresca());
        if (frutas.isEmpty()) {
            throw new EntityNotFoundException("Nenhuma fruta encontrada com os parâmetros informados!");
        }
        return frutas;
    }

    public List<Fruta> getFruitsByClassification(FrutasFindBysDTO dto){
        ClassificacaoEnum classificacao = ClassificacaoEnum.fromValor(dto.classificacao());
        List<Fruta> frutas = frutaRepository.findAllByClassificacaoAndOrderByNome(classificacao);
        if (frutas.isEmpty()){
            throw new EntityNotFoundException("Nenhuma fruta encontrada com a classificação " + classificacao + "!");
        }
        return frutas;
    }

    public List<Fruta> getFruitsByFreshness(FrutasFindBysDTO dto){
        List<Fruta> frutas = frutaRepository.findAllByFrescaAndOrderByNome(dto.fresca());
        if (frutas.isEmpty()){
            throw new EntityNotFoundException("Nenhuma fruta encontrada com a frescura " + dto.fresca() + "!");
        }
        return frutas;
    }

    public List<Fruta> getFruitsByAvailableQuantity(){
        return frutaRepository.findAllByQtdDisponivelNotNull();
    }

    public List<Fruta> getFruitsBySaleValueAsc(){
        return frutaRepository.findAllByValorVendaAsc();
    }

    public List<Fruta> getFruitsBySaleValueDesc(){
        return frutaRepository.findAllByValorVendaDesc();
    }

    public void saveFruit(FrutaRequestDTO dto) throws MessagingException {

        Fruta fruta = new Fruta(dto);
        Optional<Fruta> frutaExists = frutaRepository.findByNomeAndClassificacao(fruta.getNome(), fruta.getClassificacao());
        if (frutaExists.isPresent()){
            throw new IllegalArgumentException("Fruta já cadastrada");
        }

        String Message = "Fruta " + fruta.getNome() + " cadastrada com sucesso!";

        emailService.sendEmail(new EmailModelDTO(emailFrom, emailTo, "SEASA",  Message));
        frutaRepository.save(fruta);
    }
}
