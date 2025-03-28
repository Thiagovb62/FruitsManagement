package com.thiago.fruitmanagementsystem.Service;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.thiago.fruitmanagementsystem.Model.HistoricoVendaFrutas;
import com.thiago.fruitmanagementsystem.Model.VendaRequestDTO;
import com.thiago.fruitmanagementsystem.Model.HistoricoVendas;
import com.thiago.fruitmanagementsystem.Repository.HistoricoVendaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class VendasService  {

    private final HistoricoVendaRepository historicoVendaRepository;
    private final HistoricoVendaService historicoVendaService;
    private final PayPalService payPalService;


    public VendasService(HistoricoVendaRepository historicoVendaRepository, HistoricoVendaService historicoVendaService, PayPalService payPalService) {
        this.historicoVendaRepository = historicoVendaRepository;
        this.historicoVendaService = historicoVendaService;
        this.payPalService = payPalService;
    }

    public Payment executeSalesWithDiscoutOrNot( VendaRequestDTO dto) throws PayPalRESTException {

            HistoricoVendas historico = historicoVendaService.createHistoricoVendas(dto);

            List<HistoricoVendaFrutas> frutasVendidas = historicoVendaService.processFruitsSales(dto, historico);

            Double valorTotal = historico.getValorTotal();

            Payment payment = payPalService.cretePayment(valorTotal, "BRL", "paypal", "sale", "Venda de frutas", "http://localhost:8080/payment/cancel", "http://localhost:8080/payment/success");

            historico.setFrutasVendidas(frutasVendidas);

            historicoVendaRepository.save(historico);

            return payment;
        }

}
