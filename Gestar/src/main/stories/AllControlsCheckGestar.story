Narrative:
Ingreso a Gestar, instancia DESA como usuario ADMIN
Entro a la carpeta "autoMate"
Entro al formulario "autoMate - prueba"
Selecciono el tab2
Selecciono el tab1
Cargo el campo textbox1 con texto plano

Scenario: entro a automate y pruebo los tabs y el control de texto
Given Navigate to the http://10.201.4.191/w/ url in the chrome browser
When me conecto a gestar con el usuario: admin sin pass a la instancia DESA
When abrir la carpeta autoMate
When abrir la carpeta autoMATE - prueba
When crear arhivo nuevo
!-- When seleccionar tabPanel tab2
!-- When seleccionar tabPanel tab3
!-- When seleccionar tabPanel tab4
!-- When seleccionar tabPanel tab5
!-- When seleccionar tabPanel tab1
!-- When ingreso abc para el campo requerido: RequiredTextBox
!-- When ingreso lala en comunTextBox
!-- When ingreso 123 para el campo numerico: NumericTexBox
!-- When ingreso jojojujaju para campo de texto multiple: MultiTexBox
!-- When ingreso lapolola para el campo password: PassTexBox
!-- When seleccionar tabPanel tab2
!-- When adjuntar el archivo: C:/lala.txt, en andjunto: attachments
!-- When seleccionar tabPanel tab3
!-- When cargo manualmente la fecha: 07/07/2017, con 03:24 en DTPicker: DTPciker
!-- When cargo por calendario la fecha: 07/07/2017, en DTPicker: DTPickerF
!-- When cargo por calendario la fecha: 27/07/2017, en DTPicker: DTPickerF
!-- When cargo por calendario la fecha: 01/07/2017, en DTPicker: DTPickerF
!-- When cargo por calendario la fecha: 31/12/2017, en DTPicker: DTPickerF
!-- When cargo por calendario la fecha: 01/01/2018, en DTPicker: DTPickerF
!-- When cargo por calendario la fecha: 01/01/2017, en DTPicker: DTPickerF
When seleccionar tabPanel tab5
When selecciono las opciones:
|options|
|Jose|
|Juan|
del selector multiple: select11
