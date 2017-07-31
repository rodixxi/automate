Narrative:
Ingreso a Gestar, instancia DESA como usuario ADMIN
Entro a la carpeta "autoMate"
Entro al formulario "autoMate - prueba"
Selecciono el tab2
Selecciono el tab1
Cargo el campo textbox1 con texto plano

!-- Given Navigate to the http://10.201.4.191/w/ url in the chrome browser
!-- When me conecto a gestar con el usuario: admin sin pass a la instancia DESA.
!--When abrir la carpeta autoMate
!-- When abrir la carpeta autoMATE - prueba
!-- When crear arhivo nuevo.

Scenario: me loge a gestar
Given Navigate to the http://10.201.4.191/w/ url in the chrome browser
And me conecto a gestar con el usuario: admin sin pass a la instancia DESA.

Scenario: pruebo los tabs
Given abrir la carpeta de automate y creo un nuevo formulario.

When seleccionar tabPanel: tab1.
And ingreso abc; para el campo requerido: RequiredTextBox.
And ingreso lala; para el textBox: comunTextBox.
And ingreso 123; para el textBox numerico: NumericTexBox.
And ingreso jojojujaju asdasd asdasdas; para textArea: MultiTexBox.
And ingreso lapolola; para el textBox password: PassTexBox.

And seleccionar tabPanel: tab2.
And adjuntar el archivo: C:\lala.txt; en andjunto: attachments.

And seleccionar tabPanel: tab3.
And cargo manualmente la fecha: 07/07/2017, con 03:24 en DTPicker: DTPciker.
And cargo por calendario la fecha: 07/07/2017, en DTPicker: DTPickerF.
And cargo por calendario la fecha: 27/07/2017, en DTPicker: DTPickerF.
And cargo por calendario la fecha: 01/07/2017, en DTPicker: DTPickerF.
And cargo por calendario la fecha: 31/12/2017, en DTPicker: DTPickerF.
And cargo por calendario la fecha: 01/01/2018, en DTPicker: DTPickerF.
And cargo por calendario la fecha: 01/01/2017, en DTPicker: DTPickerF.

And seleccionar tabPanel: tab4.
And busco: Administrador; en el control con DobleClick LookUpBoxAccount: LookupBoxAccounts.

And seleccionar tabPanel: tab5.
And selecciono las opciones:
|options|
|Juan|
del Selector multiple: select11.
And deselecciono las opciones:
|options|
|Jose|
del selector multiple: select11.
And selecciono todas las opciones del selector multiple: select11.
And deselecciono todas las opciones del selector multiple: select11.
And busco : Avecilla, Rebeca; en el control de autocompletado: autoComplete1.
And busco : Avecilla, Rebeca; en el control de autocompletado: autoComplete2.
And guardar y salir del formulario.
Then estas a nivel de folder.