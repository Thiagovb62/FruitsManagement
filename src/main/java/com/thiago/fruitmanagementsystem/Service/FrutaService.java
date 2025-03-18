package com.thiago.fruitmanagementsystem.Service;

import com.thiago.fruitmanagementsystem.Enums.ClassificacaoEnum;
import com.thiago.fruitmanagementsystem.Model.Fruta;
import com.thiago.fruitmanagementsystem.Model.FrutasFindBysDTO;
import com.thiago.fruitmanagementsystem.Model.FrutaRequestDTO;
import com.thiago.fruitmanagementsystem.Repository.FrutaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FrutaService {

    private final FrutaRepository frutaRepository;

    public FrutaService(FrutaRepository frutaRepository) {
        this.frutaRepository = frutaRepository;
    }

    public Fruta findFruitByName(FrutasFindBysDTO dto){
        var name = dto.nome();
        return frutaRepository.findByNome(name).orElseThrow(() -> new NullPointerException("Fruta não encontrada"));
    }

    public List<Fruta> getAllFruits(){
        return frutaRepository.findAll();
    }

    public List<Fruta> findAllByClassificacaoOrFrescaAndOrderByValorVendaIdAsc(FrutasFindBysDTO dto) {
        List<Fruta> frutas = frutaRepository.findAllByClassificacaoAndFrescaAndOrderByValorVendaIdAsc(ClassificacaoEnum.fromValor(dto.classificacao()), dto.fresca());
        if (frutas.isEmpty()) {
            throw new NullPointerException("Nenhuma fruta encontrada com os parâmetros informados!");
        }
        return frutas;
    }

    public List<Fruta> getFruitsByClassification(FrutasFindBysDTO dto){
        ClassificacaoEnum classificacao = ClassificacaoEnum.fromValor(dto.classificacao());
        List<Fruta> frutas = frutaRepository.findAllByClassificacaoAndOrderByNome(classificacao);
        if (frutas.isEmpty()){
            throw new NullPointerException("Nenhuma fruta encontrada com a classificação " + classificacao + "!");
        }
        return frutas;
    }

    public List<Fruta> getFruitsByFreshness(FrutasFindBysDTO dto){
        List<Fruta> frutas = frutaRepository.findAllByFrescaAndOrderByNome(dto.fresca());
        if (frutas.isEmpty()){
            throw new NullPointerException("Nenhuma fruta encontrada com a frescura " + dto.fresca() + "!");
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

    public void saveFruit(FrutaRequestDTO dto){

        Fruta fruta = new Fruta(dto);
        Optional<Fruta> frutaExists = frutaRepository.findByNome(fruta.getNome());
        if (frutaExists.isPresent()){
            throw new IllegalArgumentException("Fruta já cadastrada");
        }

        frutaRepository.save(fruta);
    }
}
