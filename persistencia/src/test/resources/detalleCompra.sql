insert into ciudad values(1,"Armenia");
insert into ciudad values (2,"Cali");
insert into ciudad values (3,"Medellin");
insert into usuario(cod_persona,email,nombre,password,telefono,ciudad_usuario_cod_ciudad) values("123", "karla@gmail.com", "karla", "111", "3107675467", 1);
insert into usuario(cod_persona,email,nombre,password,telefono,ciudad_usuario_cod_ciudad) values("435", "maria@gmail.com", "maria","564", "3237675467",2);
insert into usuario(cod_persona,email,nombre,password,telefono,ciudad_usuario_cod_ciudad) values("654", "oscar@gmail.com", "oscar", "654","3107675467", 3);

insert into producto(cod_producto,descripcion,descuento,fecha_limite,nombre,precio,unidades,ciudad_producto_cod_ciudad,usuario_vendedor_cod_persona) values(1, "es bonito", 15.0,"2022/6/25","Televisor",15504.4, 40,1,"123");
insert into producto(cod_producto,descripcion,descuento,fecha_limite,nombre,precio,unidades,ciudad_producto_cod_ciudad,usuario_vendedor_cod_persona) values(2, "esta shido", 15.0,"2023/6/25","computador",1550554.4, 40,1,"435");
insert into producto(cod_producto,descripcion,descuento,fecha_limite,nombre,precio,unidades,ciudad_producto_cod_ciudad,usuario_vendedor_cod_persona) values(3, "esta nuevo", 25.0,"2023/6/8","bicicleta",1550554.4, 40,1,"654");
insert into producto_imagenes values(1, "televisor");
insert into producto_imagenes values(2, "Celular");
insert into producto_imagenes values(3, "mango");
insert  into compra(cod_compra,fecha_pago, medio_pago, usuario_compra_cod_persona) values (1,"2022/6/20","nequi","123");
insert  into compra(cod_compra,fecha_pago, medio_pago, usuario_compra_cod_persona) values (2,"2022/6/11","daviplata","435");
insert  into compra(cod_compra,fecha_pago, medio_pago, usuario_compra_cod_persona) values (3,"2022/6/10","efecty","654");
insert into detalle_Compra(cod_detalle,precio_producto,unidades,producto_detalle_cod_producto,compradetalle_cod_compra) values (1,45000001,3,1,3);
insert into detalle_Compra(cod_detalle,precio_producto,unidades,producto_detalle_cod_producto,compradetalle_cod_compra) values (2,2500000.1,2,2,1);
insert into detalle_Compra(cod_detalle,precio_producto,unidades,producto_detalle_cod_producto,compradetalle_cod_compra) values (3,5000000.1,1,3,2);

