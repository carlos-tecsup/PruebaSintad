

## Configuracion entorno en  ambiente local


## Script SQL
Ejecutar el script SQL
```sql
create database mantenimiento;
use mantenimiento;

INSERT INTO `tb_rol` values(1,'ADMIN');
INSERT INTO `tb_user` values(1,'test1@gmail.com','123');
INSERT INTO `usuario_roles` values(1,1);

INSERT INTO `tb_entidad` VALUES ('1', 'Jr. Comandante Jimenez Nro. 166 Int. a (entre Cuadra 7 y 8 Javier Padro Oeste)','1','SYL CARGO NOMBRE COMERCIAL', '20505327552', 'SYL S.A.C','79845612',1, 1);

INSERT INTO `tb_tipo_contribuyente` VALUES ('1', '1', 'Natural Sin Negocio');
INSERT INTO `tb_tipo_contribuyente` VALUES ('2', '1', 'Juridica');
INSERT INTO `tb_tipo_contribuyente` VALUES ('3', '1', 'Natural Con Negocio');
INSERT INTO `tb_tipo_contribuyente` VALUES ('4', '1', 'No Domiciliado');


INSERT INTO `tb_tipo_documento` VALUES ('1', '4', 'CARNET DE EXTRANJERIA','1', 'CARNET DE EXTRANJERIA');
INSERT INTO `tb_tipo_documento` VALUES ('2', '7', 'PASAPORTE','1', 'PASAPORTE');
INSERT INTO `tb_tipo_documento` VALUES ('3', '11', 'PARTIDA DE NACIMIENTO - IDENTIDAD','1', 'PARTIDA DE NACIMIENTO');
INSERT INTO `tb_tipo_documento` VALUES ('4', '99', 'OTROS', '1','OTROS');
INSERT INTO `tb_tipo_documento` VALUES ('5', '6', 'REGISTRO UNICO DEL CONTRIBUYENTE','1', 'RUC');
```
## Swagger
```
http://localhost:8080/swagger-ui/index.html
```

## Frontend

Realizar el siguiente comando para instalar las dependencias
```
npm install
```
Levantar el servidor  
```
ng serve -o
```
Url: http://localhost:4200/


