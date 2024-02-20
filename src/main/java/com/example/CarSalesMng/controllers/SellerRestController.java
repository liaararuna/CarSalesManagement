package com.example.CarSalesMng.controllers;

import com.example.CarSalesMng.models.dto.SellerDTO;
import com.example.CarSalesMng.services.SellerService;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class SellerRestController {

    @Autowired
    private SellerService sellerService;

    @GetMapping(value = "/sellers", produces = "application/json")
    public CollectionModel<SellerDTO> getAllSellers() {
        List<SellerDTO> sellerDTOList = this.sellerService.getAllSellers();

        for(SellerDTO sellerDTO : sellerDTOList) {
            sellerDTO.add(linkTo(methodOn(SellerRestController.class).getSeller(sellerDTO.getId())).withSelfRel());
        }

        Link link = linkTo(methodOn(SellerRestController.class).getAllSellers()).withSelfRel();
        CollectionModel<SellerDTO> resp = CollectionModel.of(sellerDTOList, link);
        return resp;
    }

    @GetMapping(value = "/sellers/{id}", produces = "application/json")
    public ResponseEntity<SellerDTO> getSeller(@PathVariable("id") int id) {
        SellerDTO sellerDTO = this.sellerService.getSellerById(id);

        if(sellerDTO == null) { return new ResponseEntity<>(HttpStatus.NOT_FOUND); }

        sellerDTO.add(linkTo(methodOn(SellerRestController.class).getAllSellers()).withSelfRel());

        return new ResponseEntity<>(sellerDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/seller", produces = "application/json", consumes = "application/json")
    public ResponseEntity<SellerDTO> addSeller(@RequestBody SellerDTO sellerDTO) {
        if(sellerDTO == null) { new ResponseEntity<>(HttpStatus.BAD_REQUEST); }

        SellerDTO newSellerDTO = this.sellerService.add(sellerDTO);
        newSellerDTO.add(linkTo(methodOn(SellerRestController.class).getSeller(sellerDTO.getId())).withSelfRel());
        newSellerDTO.add(linkTo(methodOn(SellerRestController.class).getAllSellers()).withRel("see_all_sellers"));

        return new ResponseEntity<>(newSellerDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/seller/{id}", produces = "application/json")
    public ResponseEntity<SellerDTO> updateSeller(@PathVariable("id") int id,
                                            @RequestBody SellerDTO sellerDTO) {

        sellerDTO.setId(id);
        SellerDTO updatedSeller = this.sellerService.update(sellerDTO);

        updatedSeller.add(linkTo(methodOn(SellerRestController.class).getSeller(sellerDTO.getId())).withSelfRel());
        updatedSeller.add(linkTo(methodOn(SellerRestController.class).getAllSellers()).withRel("see_all_sellers"));

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
