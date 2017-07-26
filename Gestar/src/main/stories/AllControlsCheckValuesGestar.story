Narrative:
Esta historia corrobora que todas los contrles
de gestar tengan correctamente sus valores

Scenario: conprueva los datos del primer tab
Given seleccionado el formulario donde el campo: comunTextBox; es igual a: adasdas.
When seleccionar tabPanel: tab1.
Then el campo textBox: comunTextBox; tiene el valor: adasdas.
And el campo textBox requerido: RequiredTextBox; tiene el valor: dgdgdfgd.
And el campo textBox numerico: NumericTexBox; tiene el valor: 3454353.
And el campo textArea: MultiTexBox; tiene el valor: sdfsdfsdfsdfsf s fsdffs sdfs df sdfs fsdf  s.
And el campo textBox password: PassTexBox; tiene el valor: 123qwe.
When seleccionar tabPanel: tab2.
Then el campo andjunto: attachments; tiene el archivo: lala.txt
Then el checkbox: checkBox; esta seleccionado

