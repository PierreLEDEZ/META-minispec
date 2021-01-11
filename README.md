# META-minispec

This project is part of the MSc of Computer Science (SIIA) at the University of Western Brittany. It was developped by Pierre LE DEZ and Ilias MAOUDJ in december 2020.

## UML model
<p><img src="http://www.plantuml.com/plantuml/png/RP7FJiCm3CRlUGfhHwHEErS8ROSTTW0XXhY7rcij4cUANS4OUtVQbQufwit-VL_ynrcBsgYDTqO-Y8ysMWy-S2Zg63o2nWOJHMfRXa_Y2WxMIQeKpefiL2EzDugx8l2UlX5MnbmLUB8Uowr3ZwnmDW3SWXps6jjgvZBO7aE93iuZsBAIdb9oQt8jewVZJ9cDI2Z8AWM-kZnn3h1ZnGfZbzklIIxjhFcOJwi2X4FhJfgOoJRtFrdM3px3-_bdXDiQSzeulZ7zIR98LQtP0onMo-NTOhXB3jW5X69wyjyuzid13k5eRd-J4-_OjtMFYGntVICX3H7Ebp_m78aL4_22R3rnzHg2wwWPddIB3VOfJ9yOKrE9K50i7FQnHwr3AMQDN3RU_G80" /></p>

## Model creation
A model's declaration is done using an xml file, it starts with a &lt;Start&gt; tag with model's name.  
A model element must have a name, just like entity elements, attribute elements and association elements.  
Except the elements "attribute" and "association", the others must be nested.

### Example

```xml
<Start model="model_example">
    <model name="model_example">
        <entity name="entity_1">
            <attribute name="attribute_1" type="String"/>
        </entity>
    </model>
</Start>
```
