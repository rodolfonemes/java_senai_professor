//https://docs.spring.io/spring-boot/docs/current/reference/html/howto-database-initialization.html
INSERT INTO `cadastroboot`.`users` (`username`,`password`,`enabled`) VALUES ('admin','$2a$10$kRG/kiV2gNHsAwELE6JSUeocKRDL.l4koIA0w3VZqcHiwjvAs0Ugy',1),('usuario','$2a$10$kRG/kiV2gNHsAwELE6JSUeocKRDL.l4koIA0w3VZqcHiwjvAs0Ugy',1);
INSERT INTO `cadastroboot`.`authorities` (`username`, `authority`) VALUES ('admin','ROLE_ADMIN'),('usuario','ROLE_USER');
INSERT INTO `cadastroboot.`Servico` (`idServico`,`nome`,`desativado`) VALUES (1,'Fatura Digital','\0'),(2,'Fatura por E-Mail','\0'),(3,'Débito Automático','\0'),(7,'Wi-Fi','\0');
