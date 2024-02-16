package com.example.CarSalesMng.controllers;

import com.example.CarSalesMng.models.Seller;
import com.example.CarSalesMng.models.dto.SellerDTO;
import com.example.CarSalesMng.services.SellerService;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class SellerRestController {

    @Autowired
    private SellerService sellerService;

    @GetMapping(value = "/sellers", produces = "application/json")
    public CollectionModel<SellerDTO> getAllSellers() {
        List<Seller> sellerList = this.sellerService.getAllSellers();

        if(sellerList == null) { throw new NotImplementedException(); }

        List<SellerDTO> sellerDTOList = new ArrayList<>();

        for(Seller seller : sellerList) {
            SellerDTO sellerDTO = new SellerDTO(seller.getId(), seller.getName(), seller.getNif(), seller.getAddress(), seller.getPhoneNumber());
            sellerDTO.add(linkTo(methodOn(SellerRestController.class).getSeller(sellerDTO.getId())).withSelfRel());
            sellerDTOList.add(sellerDTO);
        }

        Link link = linkTo(methodOn(SellerRestController.class).getAllSellers()).withSelfRel();
        CollectionModel<SellerDTO> resp = CollectionModel.of(sellerDTOList, link);
        return resp;
    }

    @GetMapping(value = "/sellers/{id}", produces = "application/json")
    public ResponseEntity<SellerDTO> getSeller(@PathVariable("id") int id) {
        Seller seller = this.sellerService.getSellerById(id);

        if(seller == null) { return new ResponseEntity<>(HttpStatus.NOT_FOUND); }

        SellerDTO sellerDTO = new SellerDTO(seller.getId(), seller.getName(), seller.getNif(), seller.getAddress(), seller.getPhoneNumber());
        sellerDTO.add(linkTo(methodOn(SellerRestController.class).getAllSellers()).withSelfRel());

        return new ResponseEntity<>(sellerDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/seller", produces = "application/json", consumes = "application/json")
    public ResponseEntity<SellerDTO> addSeller(@RequestBody SellerDTO sellerDTO) {
        if(sellerDTO == null) { throw new IllegalArgumentException(); }

        Seller newSeller = new Seller(sellerDTO.getId(), sellerDTO.getName(), sellerDTO.getNif(), sellerDTO.getAddress(), sellerDTO.getPhoneNumber());
        this.sellerService.add(newSeller);

        SellerDTO otherSellerDTO = new SellerDTO(newSeller.getId(), newSeller.getName(), newSeller.getNif(), newSeller.getAddress(), newSeller.getPhoneNumber());
        otherSellerDTO.add(linkTo(methodOn(SellerRestController.class).getSeller(newSeller.getId())).withSelfRel());
        otherSellerDTO.add(linkTo(methodOn(SellerRestController.class).getAllSellers()).withRel("see_all_sellers"));

        return new ResponseEntity<>(otherSellerDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/sellers/{id}", produces = "application/json")
    public ResponseEntity<SellerDTO> update(@PathVariable("id") int id,
                                            @RequestBody SellerDTO sellerDTO) {
        if(sellerDTO.getId() != id) { return new ResponseEntity<>(HttpStatus.NOT_FOUND); }

        Seller updatedSeller = new Seller(sellerDTO.getId(), sellerDTO.getName(), sellerDTO.getNif(), sellerDTO.getAddress(), sellerDTO.getPhoneNumber());
        sellerService.update(updatedSeller);

        SellerDTO otherSellerDTO = new SellerDTO(updatedSeller.getId(), updatedSeller.getName(), updatedSeller.getNif(), updatedSeller.getAddress(), updatedSeller.getPhoneNumber());
        otherSellerDTO.add(linkTo(methodOn(SellerRestController.class).getSeller(updatedSeller.getId())).withSelfRel());
        otherSellerDTO.add(linkTo(methodOn(SellerRestController.class).getAllSellers()).withRel("see_all_sellers"));

        return new ResponseEntity<>(otherSellerDTO, HttpStatus.OK);
    }
}
