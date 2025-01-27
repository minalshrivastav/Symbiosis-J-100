package com.example.purchase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.purchase.PurchaseOrder;
import com.example.purchase.PurchaseOrderRepository;

@DataJpaTest
public class PurchaseAppApplicationTests {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Test
    public void testSavePurchaseOrder() {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrderRepository.save(purchaseOrder);

        Long id = purchaseOrder.getId();
        PurchaseOrder savedOrder = purchaseOrderRepository.findById(id).get();

        // Assert that the saved order matches the created order
        assertEquals("Laptop", savedOrder.getItem());
        assertEquals(1, savedOrder.getQuantity());
        assertEquals("ABC Electronics", savedOrder.getSupplier());
        assertEquals("XYZ123", savedOrder.getBatchNo());
    }
}