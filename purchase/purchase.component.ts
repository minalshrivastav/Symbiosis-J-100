import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { PurchaseService } from 'src/app/purchase.service';

@Component({
  selector: 'app-purchase',
  templateUrl: './purchase.component.html',
  styleUrls: ['./purchase.component.css'] // Note the plural "styleUrls"
})
export class PurchaseComponent implements OnInit {  // it is lifecycle hook in angular which is atomatically called when a component is initialized
  private apiUrl = 'http://localhost:8080/api/purchase-orders';

  
  suppliers = ['HeartCare Ltd.', 'MediCure Pvt Ltd ', 'HealWell Labs', 'DiabCare Inc.','NutriLife Pharma','ABC Pharma','XYZ Pharma','ThyroidCare Pharma','DigestiveHealth Pharma','PainRelief Pharma  ','AntiMalarial Inc.','Lipids Control Pharma ','AcidEase Pharma '];

  newOrder = { item: '', quantity: 0, supplier: '', batchNo: '' };
    

    constructor(private purchaseService: PurchaseService) {}
ngOnInit(): void {   // it is a lifecycle hook provided byy the angular specifically designed for component
  this.loadOrders();
}
loadOrders(): void {
  this.purchaseService.getOrders().subscribe((data) => {
    this.purchaseOrders = data;
  });
}
  // List of purchase orders including supplier name and batch number
  purchaseOrders: Array<{
    id: number;
    item: string;
    quantity: number;
    supplier: string;
    batchNo: string;
  }> = [];

  // Unique ID counter for orders
  private idCounter = 1;

  // Add a new order
  addOrder(event: Event) {
    event.preventDefault();
    if (this.newOrder.item && this.newOrder.quantity && this.newOrder.supplier && this.newOrder.batchNo) {
      this.purchaseService.addOrder(this.newOrder).subscribe(
        () => {
          alert('Order placed successfully!');
          this.newOrder = { item: '', quantity: 0, supplier: '', batchNo: '' }; // Reset form
          this.loadOrders(); // Refresh list
        },
        (error) => {
          alert('Error placing order.');
          console.error('Error:', error);
        }
      );
    } else {
      alert('Please fill all fields.');
    }
  }
  
  

  deleteOrder(orderId: number): void {
this.purchaseService.deleteOrder(orderId).subscribe(() => {
  this.loadOrders(); // Refresh the list
  alert('Order deleted successfully!');
});
}

// Reorder an existing order
reorderProduct(orderId: number): void {
const orderToReorder = this.purchaseOrders.find((order) => order.id === orderId);
if (orderToReorder) {
  const reordered = {
    ...orderToReorder,
    id: this.idCounter++,
  };
  this.purchaseOrders.push(reordered);
  alert('Reorder placed successfully!');
}
}
}