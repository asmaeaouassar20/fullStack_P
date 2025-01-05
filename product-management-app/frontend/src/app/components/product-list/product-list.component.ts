import { Component, NgModule, OnInit } from '@angular/core';
import { Product, ProductService } from '../../services/product.service';
import { NgFor, NgIf } from '@angular/common';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-product-list',
  imports: [NgFor,NgIf,FormsModule],
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.css'
})
export class ProductListComponent implements OnInit{

  products:Product[]=[];

  constructor(private productService:ProductService){}

  ngOnInit(): void {
     this.getProducts();
  }

  getProducts() : void{
    this.productService.getProducts().subscribe(
      products => this.products=products
    );
  }


  addProduct(name:string,description:string,price:number) : void{
    const product:Product = {name,description,price} 
    this.productService.createproduct(product).subscribe(
      product => this.products.push(product)
    )
  }


    updateProduct(product:Product){
      this.productService.updateProduct(product.id!,product).subscribe( updatedProduct =>
      {
        // Mettre à jour la liste des produits dans l'interface utilisateur
        const index = this.products.findIndex(p=> p.id==updatedProduct.id);
        if(index!=-1){
          this.products[index]=updatedProduct;
        }
      }
      );
    }

    deleteProduct(id:number):void{
      this.productService.deleteProduct(id).subscribe(
        ()=>this.products=this.products.filter(p=>p.id!==id)
      );
    }


}
