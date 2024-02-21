package com.example.CarSalesMng.controllers;

import com.example.CarSalesMng.models.dto.SellerDTO;
import com.example.CarSalesMng.services.SellerService;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class SellerRestController {

    @Autowired
    private SellerService sellerService;

    @GetMapping(value = "/sellers", produces = "application/json")
    public CollectionModel<SellerDTO> getAllSellers(@RequestParam Optional<Integer> page,
                                                    @RequestParam Optional<Integer> size) {
        //Armazena os valores de page e size passados como parâmetro na url nas variáveis abaixo:
        int _page = page.orElse(0);
        int _size = size.orElse(10);

        //Cria um objeto Pageable (uma página) através do retorno do método getAllSellers do SellerService passando como parâmetro as variáveis acima.
        Page<SellerDTO> sellerDTOList = this.sellerService.getAllSellers(_page, _size);

        //Adiciona o link para cada objeto presente na página.
        for(SellerDTO sellerDTO : sellerDTOList) {
            sellerDTO.add(linkTo(methodOn(SellerRestController.class).getSeller(sellerDTO.getId())).withSelfRel());
        }

        //Cria um objeto do tipo Link com o link que será incorporado a cada objeto exibido.
        Link link = linkTo(methodOn(SellerRestController.class).getAllSellers(Optional.of(_page),Optional.of(_size))).withSelfRel();
        //Cria uma lista de links vazia.
        List<Link> links = new ArrayList<>();
        //Adiciona o objeto do tipo Link à lista.
        links.add(link);

        //Avalia se a página exibida NÃO é a última página.
        if(!sellerDTOList.isLast()) {
            //Caso não seja a última página, acrescenta o link para acesso à página seguinte.
            Link _link = linkTo(methodOn(SellerRestController.class).getAllSellers(Optional.of(_page + 1), size)).withRel("next");
            links.add(_link);
        }

        //Avalia se a página exibida NÃO é a primeira página.
        if(!sellerDTOList.isFirst()) {
            //Caso não seja a última página, acrescenta o link para acesso à página anterior.
            Link _link = linkTo(methodOn(SellerRestController.class).getAllSellers(Optional.of(_page - 1), size)).withRel("previous");
            links.add(_link);
        }

        //Retorna uma CollectionModel de páginas com objetos e seus links.
        return CollectionModel.of(sellerDTOList, link);
    }

    @GetMapping(value = "/sellers/{id}", produces = "application/json")
    public ResponseEntity<SellerDTO> getSeller(@PathVariable("id") int id) {
        SellerDTO sellerDTO = this.sellerService.getSellerById(id);

        if(sellerDTO == null) { return new ResponseEntity<>(HttpStatus.NOT_FOUND); }

        sellerDTO.add(linkTo(methodOn(SellerRestController.class).getAllSellers(Optional.of(1),Optional.of(10))).withSelfRel());

        return new ResponseEntity<>(sellerDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/seller", produces = "application/json", consumes = "application/json")
    public ResponseEntity<SellerDTO> addSeller(@RequestBody SellerDTO sellerDTO) {
        if(sellerDTO == null) { new ResponseEntity<>(HttpStatus.BAD_REQUEST); }

        SellerDTO newSellerDTO = this.sellerService.add(sellerDTO);
        newSellerDTO.add(linkTo(methodOn(SellerRestController.class).getSeller(sellerDTO.getId())).withSelfRel());
        newSellerDTO.add(linkTo(methodOn(SellerRestController.class).getAllSellers(Optional.of(1),Optional.of(10))).withRel("see_all_sellers"));

        return new ResponseEntity<>(newSellerDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/seller/{id}", produces = "application/json")
    public ResponseEntity<SellerDTO> updateSeller(@PathVariable("id") int id,
                                            @RequestBody SellerDTO sellerDTO) {

        sellerDTO.setId(id);
        SellerDTO updatedSeller = this.sellerService.update(sellerDTO);

        updatedSeller.add(linkTo(methodOn(SellerRestController.class).getSeller(sellerDTO.getId())).withSelfRel());
        updatedSeller.add(linkTo(methodOn(SellerRestController.class).getAllSellers(Optional.of(1),Optional.of(10))).withRel("see_all_sellers"));

        return new ResponseEntity<>(updatedSeller, HttpStatus.OK);
    }

    @DeleteMapping(value = "/car/{id}", produces = "application/json")
    public ResponseEntity<SellerDTO> deleteSeller(@PathVariable("id") int id) {
        SellerDTO sellerDTO = this.sellerService.getSellerById(id);

        if (sellerDTO != null){
            SellerDTO deletedSeller = this.sellerService.deleteById(id);
            return new ResponseEntity<>(deletedSeller,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
