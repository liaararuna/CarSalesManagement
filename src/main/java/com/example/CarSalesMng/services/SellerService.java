package com.example.CarSalesMng.services;

import com.example.CarSalesMng.data.SellerRepository;
import com.example.CarSalesMng.enums.CarStatus;
import com.example.CarSalesMng.models.Car;
import com.example.CarSalesMng.models.Seller;
import com.example.CarSalesMng.models.dto.CarDTO;
import com.example.CarSalesMng.models.dto.SellerDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SellerService {
    @Autowired
    private SellerRepository sellerRepository;

    public SellerDTO getSellerById(int id) {
        Seller seller = this.sellerRepository.findById(id).orElse(null);

        if(seller == null) {
            //TODO: throw new EntityDoesntExistException();
            throw new IllegalArgumentException("EntityDoesntExistException");
        }

        return new SellerDTO(seller);
    }

    public Page<SellerDTO> getAllSellers(int page, int size) {
        //Armazena no Page os dados com os critérios passados como parâmetro.
        Page<Seller> sellerPage = this.sellerRepository.findAll(PageRequest.of(page,size));

        //Avaliar se a Page tem conteúdo e, caso não tenha, lança exceção.
        if(!sellerPage.hasContent()) {
            //TODO: throw new TableIsEmptyException();
            throw new IllegalArgumentException("TableIsEmptyException");
        }

        //Instancia uma Page<SellerDTO> obtendo o conteúdo da Page<Seller>, O
        Page<SellerDTO> sellerDTOPage = new PageImpl<>(sellerPage.getContent()
                //Obtem o conteúdo da page e converte em uma stream
                .stream()
                //Converte todos os objetos da page em SellerDTO
                .map(SellerDTO::new)
                //Cria uma lista com os elementos de informação presentes na página e todos os seus elementos.
                .collect(Collectors.toList()), sellerPage.getPageable(), sellerPage.getTotalElements());

        return sellerDTOPage;
    }

    @Transactional
    public SellerDTO add(SellerDTO sellerDTO) {
        //Validação se o seller já existe no banco de dados.
        Seller seller = this.sellerRepository.findById(sellerDTO.getId()).orElse(null);

        if(seller != null) {
            //TODO: throw new EntityAlreadyExistsException();
            throw new IllegalArgumentException("EntityAlreadyExistsException");
        }

        //Criação de um objeto do tipo Seller para ser salvo no banco de dados.
        Seller newSeller = new Seller(sellerDTO.getId(),
                sellerDTO.getName(),
                sellerDTO.getNif(),
                sellerDTO.getAddress(),
                sellerDTO.getPhoneNumber(),
                new ArrayList<>(sellerDTO.getCarList())
        );

        return new SellerDTO(newSeller);
    }

    @Transactional
    public SellerDTO update(SellerDTO updatedSellerDTO) {

        Seller newSeller = new Seller(
                updatedSellerDTO.getId(),
                updatedSellerDTO.getName(),
                updatedSellerDTO.getNif(),
                updatedSellerDTO.getAddress(),
                updatedSellerDTO.getPhoneNumber(),
                updatedSellerDTO.getCarList()
        );

        this.sellerRepository.save(newSeller);

        return updatedSellerDTO;
    }

    @Transactional
    public SellerDTO deleteById(int id) {
        Seller seller = this.sellerRepository.findById(id).get();

        if(seller == null) {
            //TODO: throw new EntityDoesntExistException();
            throw new IllegalArgumentException("EntityDoesntExistException");
        }

        this.sellerRepository.deleteById(id);
        return new SellerDTO(seller);
    }

}
