Narrative:
Esta historia corrobora que todas los contrles
de gestar tengan correctamente sus valores

Scenario: conprueva los datos del primer tab
Given seleccionado el formulario donde el campo: comunTextBox; es igual a: adasdas.
When seleccionar tabPanel: tab1.
Then el campo textBox: comunTextBox; tiene el valor: adasdas.
