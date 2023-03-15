package me.cal1br.webserverprogramming.api.product.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.cal1br.webserverprogramming.api.base.model.BaseDTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/* TODO
*         ▪ Наименование на продукт - текст с дължина не по-голяма от 50 символа.
Задължително поле.
        ▪ Описание на продукт - многоредово поле с възможност за въвеждане на текст
не по-дълъг от 2000 символа. Полето не е задължително.
        ▪ Поле за качване на файл - разрешава се да се качват само jpeg, jpg и png
файлове. Полето не е задължително.
        ▪ Цена на закупуване - цената, на която е бил закупен даденият продукт.
Задължително поле.
        ▪ Цена на продаване - продажната цена на продукта. Задължително поле.
        ▪ Брой продукти - Положително число или нула. Задължително поле.
        ▪ Категория: падащо меню с 3 опции: (Хранителни стоки, Канцеларски
материали, Строителни материали)
        ▪ Код - уникален код на продукт(необходимо е да има проверка за уникалност)*/
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductDTO extends BaseDTO {
    @NotNull
    @Size(max = 50)
    private String productName;
    private String productDescription;
    private String image;
    @NotNull
    private Double priceBuy; //the price that the item was bought for
    @NotNull
    private Double priceSell;
    @NotNull
    private Integer productCount;
    private ProductCategory productCategory;
    @NotNull
    private String skuCode;
}