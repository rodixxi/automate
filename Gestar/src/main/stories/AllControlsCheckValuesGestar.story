Narrative:
Esta historia corrobora que todas los contrles
de gestar tengan correctamente sus valores

Scenario: conprueva los datos del primer tab
Given Navigate to the http://10.201.4.191/w/ url in the chrome browser
And me conecto a gestar con el usuario: admin sin pass a la instancia DESA.
And abrir la carpeta de automate.
And seleccionado el formulario donde el campo: comunTextBox; es igual a: adasdas.
When seleccionar tabPanel: tab1.
Then el campo textBox: comunTextBox; tiene el valor: adasdas.
And el campo textBox requerido: RequiredTextBox; tiene el valor: dgdgdfgd.
And el campo textBox numerico: NumericTexBox; tiene el valor: 3454353.
And el campo textArea: MultiTexBox; tiene el valor: sdfsdfsdfsdfsf s fsdffs sdfs df sdfs fsdf  s.
And el campo textBox password: PassTexBox; tiene el valor: 123qwe.
When seleccionar tabPanel: tab2.
Then el campo andjunto: attachments; tiene el archivo: lala.txt (4 B)
And el checkbox: checkBox; esta seleccionado
When seleccionar tabPanel: tab3.
Then el DTPicker: DTPciker; tiene la fecha y hora: 25/07/2017, con 05:03.
And el DTPicker: DTPickerF; tiene la fecha: 19/07/2017.
When seleccionar tabPanel: tab4.
Then el LookUpBoxAccount: LookupBoxAccounts; tiene seleccionado: Administrador.
When seleccionar tabPanel: tab5.
Then el Select: selectFolder; tiene seleccionado: Jose.
And el Selector multiple: select11; selecciono las opciones:
|options|
|Juan|.
And el Selector multiple: select11; no selecciono las opciones:
|options|
|Jose|.
And el Autocomplete: autoComplete2; selecciono el valor: Avecilla, Rebeca.
And el Autocomplete: autoComplete1; selecciono los valores:
|options|
|avecillas, rebeca|
|Avecilla, Rebeca|.