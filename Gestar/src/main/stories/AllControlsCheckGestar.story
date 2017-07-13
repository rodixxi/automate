Narrative:
Ingreso a Gestar, instancia DESA como usuario ADMIN
Entro a la carpeta "autoMate"
Entro al formulario "autoMate - prueba"
Selecciono el tab2
Selecciono el tab1
Cargo el campo textbox1 con texto plano

Scenario: entro a automate y pruebo los tabs y el control de texto
Given Navigate to the http://10.201.4.191/w/ url in the firefox browser
When me conecto a gestar con el usuario: admin sin pass a la instancia DESA
When abrir la carpeta autoMate
When abrir la carpeta autoMATE - prueba
When crear arhivo nuevo
!-- When seleccionar tabPanel tab1
!-- When ingreso abc para el campo requerido: RequiredTextBox
!-- When ingreso lala en comunTextBox
!-- When ingreso 123 para el campo numerico: NumericTexBox
!-- When ingreso jojojujaju para campo de texto multiple: MultiTexBox
!-- When ingreso lapolola para el campo password: PassTexBox
!-- When seleccionar tabPanel tab2
!-- When cargar el archivo: C:/lala.txt, en popup: attachments
!-- When seleccionar tabPanel tab3
!-- When cargar fecha: 07/07/2017, con 03:24 en DTPicker: DTPcikerDyT
When seleccionar tabPanel tab5
When selecciono opcion: Juan, de el selector: selectFolder
When selecciono las opciones:
|options|
|Jose|
|Juan|
del selector multiple: select11
