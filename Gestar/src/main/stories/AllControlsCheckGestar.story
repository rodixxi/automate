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

Scenario: genero un formulario
Given abrir la carpeta de automate y creo un nuevo formulario.

When seleccionar tabPanel: tab1.
And en el textBox: comunTextBox; ingreso lala.
And en el campo requerido: RequiredTextBox; ingreso abc.
And en el textBox numerico: NumericTexBox; ingreso 123.
And en el textArea: MultiTexBox; ingreso jojojujaju asdasd asdasdas.
And en el textBox password: PassTexBox; ingreso lapolola.

And seleccionar tabPanel: tab2.
And en el campo andjunto: attachments; adjuntar el archivo: C:\lala.txt -

And seleccionar tabPanel: tab3.
And en el DTPickerOld: DTPciker; cargo manualmente la fecha: 07/07/2017, con 03:24.
And en el DTPickerOld: DTPickerF; cargo por calendario la fecha: 07/07/2017.
And en el DTPickerOld: DTPickerF; cargo por calendario la fecha: 27/07/2017.
And en el DTPickerOld: DTPickerF; cargo por calendario la fecha: 01/07/2017.
And en el DTPickerOld: DTPickerF; cargo por calendario la fecha: 31/12/2017.
And en el DTPickerOld: DTPickerF; cargo por calendario la fecha: 01/01/2018.
And en el DTPickerOld: DTPickerF; cargo por calendario la fecha: 01/01/2017.

And seleccionar tabPanel: tab4.
And en el lookUpBoxAccount: LookupBoxAccounts; busco: Administrador.
When seleccionar tabPanel: tab5.

And en el selector multiple: select11; selecciono las opciones:
|options|
|Juan|.
And en el selector multiple: select11; deselecciono las opciones:
|options|
|Jose|.
And en el selector multiple: select11; selecciono todas las opciones.
And en el selector multiple: select11; deselecciono todas las opciones.
And en el control de autocompletado: autoComplete1; busqueda multiple:
|options|
|Avecilla, Rebeca|
|avecillas, rebeca|.
And en el control de autocompletado: autoComplete2; busco: Avecilla, Rebeca.
And guardar y salir del formulario.
Then estas a nivel de folder.

Scenario: comprueba los datos del form
Given abrir la carpeta de automate.
And seleccionado el formulario donde el campo: comunTextBox; es igual a: lala.
When seleccionar tabPanel: tab1.
Then el campo textBox: comunTextBox; tiene el valor: lala.
And el campo textBox requerido: RequiredTextBox; tiene el valor: abc.
And el campo textBox numerico: NumericTexBox; tiene el valor: 123.
And el campo textArea: MultiTexBox; tiene el valor: jojojujaju asdasd asdasdas.
And el campo textBox password: PassTexBox; tiene el valor: lapolola.
When seleccionar tabPanel: tab2.
Then el campo andjunto: attachments; tiene el archivo: lala.txt (4 B)-
When seleccionar tabPanel: tab3.
Then el DTPicker: DTPciker; tiene la fecha y hora: 07/07/2017, con 03:24.
And el DTPicker: DTPickerF; tiene la fecha: 01/01/2017.
When seleccionar tabPanel: tab4.
Then el LookUpBoxAccount: LookupBoxAccounts; tiene seleccionado: Administrador.
When seleccionar tabPanel: tab5.
Then el Selector multiple: select11; no selecciono las opciones:
|options|
|Jose|
|Juan|.
And el Autocomplete: autoComplete2; selecciono el valor: Avecilla, Rebeca.
And el Autocomplete: autoComplete1; selecciono los valores:
|options|
|Avecilla, Rebeca|
|avecillas, rebeca|.

When close application