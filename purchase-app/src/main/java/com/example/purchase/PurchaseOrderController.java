package com.example.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // Combines @controller and @ResponseBody indicating that the class handles HTTP request and return data not view 
@RequestMapping("/api/purchase-orders")  // use to handle HTTP request
@CrossOrigin(origins = "http://localhost:4200") // allows cross origin request enabling sharing the resource between deffrent domain
public class PurchaseOrderController {

    @Autowired  // annotation is part of the spring framework and is used for dependency injection
    private PurchaseOrderRepository purchaseOrderRepository;

    @GetMapping // it is use to handle the HTTP get request  used to retrive the data from server
    public ResponseEntity<List<PurchaseOrder>> getAllPurchaseOrders() {
        return ResponseEntity.ok(purchaseOrderRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrder> getPurchaseOrderById(@PathVariable Long id) {
        Optional<PurchaseOrder> purchaseOrder = purchaseOrderRepository.findById(id);
        return purchaseOrder.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping // it is used to handle the HTTP post requesr to create the new resource on the server
    public ResponseEntity<PurchaseOrder> createPurchaseOrder(@RequestBody PurchaseOrder purchaseOrder) {
        return new ResponseEntity<>(purchaseOrderRepository.save(purchaseOrder), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")  // it is used to handle the HTTp put request for updating the resource on the server 
    public ResponseEntity<PurchaseOrder> updatePurchaseOrder(@PathVariable Long id, @RequestBody PurchaseOrder purchaseOrder) {
        if (!purchaseOrderRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        purchaseOrder.setId(id); 
        return ResponseEntity.ok(purchaseOrderRepository.save(purchaseOrder));
    }
 
    @DeleteMapping("/{id}") // use to delete the resource on the server
    public ResponseEntity<Void> deletePurchaseOrder(@PathVariable Long id) {
        if (purchaseOrderRepository.existsById(id)) {
            purchaseOrderRepository.deleteById(id);
            return ResponseEntity.noContent().build(); 
        }
        return ResponseEntity.notFound().build();
    }
}
