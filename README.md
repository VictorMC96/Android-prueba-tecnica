# Prueba Técnica - Desarrollador Android

## INTRODUCCIÓN
El objetivo de esta prueba es evaluar las habilidades prácticas del candidato en el desarrollo de aplicaciones Android utilizando Java y Kotlin, así como su conocimiento en el uso de interfaces gráficas con XML, implementación de patrones de diseño (MVC y MVVM), integración con APIs RESTful y aplicación de buenas prácticas de desarrollo. 
La prueba busca verificar la capacidad del desarrollador para diseñar, estructurar y mantener aplicaciones eficientes, escalables y con código limpio.

#### Criterios de Evaluación
* Creatividad para resolver los requerimientos
* Claridad, estructura y organización del código
* Atención al detalle
* Precisión en la implementación de los requisitos
* Uso adecuado de patrones de diseño
* Uso de buenas prácticas
* La aplicación debe utilizar las bibliotecas adecuadas para facilitar la implementación, sin caer en dependencias innecesarias
* La aplicación debe ser intuitiva, limpia y alineada con las pantallas proporcionadas por el equipo de diseño.

## TIEMPO ESTIMADO
2 horas

## INSTRUCCIONES

1. Responde cada sección de manera clara. Puedes elegir Java o Kotlin para las secciones que involucren código.
2. Posibilidad adaptar la interfaz de acuerdo a la imaginación del desarrollador y mejorarla desde su perspectiva
3. Se requiere de una **cuenta de GitHub** para realizar este ejercicio.
4. **Antes de comenzar a programar:**
    * Realizar un `Fork` de este repositorio
    * Clonar el fork a su máquina local  `git clone git@github.com:USERNAME/FORKED-PROJECT.git`
    * Crear un `branch` en su cuenta de GitHub utilizando su nombre completo.
5. **Al finalizar**, existen 2 opciones para entregar su proyecto:
    * 1) Realizar un `Commit` de su proyecto, **enviar un `Pull Request` al branch con su NOMBRE**, y notificar a las siguiente direcciones de correo electrónico vmiranda@neology.mx y lluna@neopartners.mx.
    * 2) Crear un archivo comprimido (_.zip_ o _.rar_) de su proyecto, envia la prueba técnica al correo de la consultora y copia a las siguientes direcciones de correo electrónico vmiranda@neology.mx y lluna@neopartners.mx.

## DESCRIPCIÓN DEL PROYECTO
La empresa requiere un sistema móvil que permita gestionar y mostrar información básica sobre sus empleados de manera accesible y eficiente. El sistema debe estar desarrollado para Android, y como parte del Departamento de Innovación, se te ha seleccionado para implementar este proyecto. El equipo de diseño ya ha proporcionado las pantallas necesarias que debes implementar. La aplicación debe ser fácil de usar y debe consumir servicios REST para obtener los datos de los empleados.

## REQUERIMIENTOS

### 1. Pantalla de Inicio (Splash Screen)
*	La pantalla de inicio debe mostrar una imagen de bienvenida durante 3 segundos.
*	Puedes utilizar una imagen proporcionada por el equipo de diseño o reemplazarla con una imagen de tu elección.
*	Después de los 3 segundos, la pantalla debe redirigir automáticamente a la Pantalla de Inicio de Sesión.

![image](https://github.com/user-attachments/assets/9fc65468-6e89-44dd-815a-5465d48ec282)

### 2. Pantalla de Inicio de Sesión
*	Esta pantalla debe permitir al usuario ingresar su ID de usuario.
*	Se debe validar que el ID ingresado sea válido. Puedes simular esta validación mediante una llamada a un servicio web.
*	Esta pantalla debe mostrarse solo una vez durante la sesión del usuario. Si el usuario cierra sesión, la pantalla de inicio de sesión debe volver a mostrarse en la próxima sesión.

![image](https://github.com/user-attachments/assets/76e13628-c9e3-4b43-bfe7-73ba0539bac2)

### 3.Lista de Empleados
*	Se debe mostrar una lista completa de todos los empleados.
*  Los datos de los empleados serán obtenidos a través de un servicio REST. Para ello, se utilizará la siguiente URL para obtener la lista de empleados:
* 		GET /api/v1/employees (http://dummy.restapiexample.com/api/v1/employees)
*	La información que se deberá mostrar de cada empleado debe incluir: **ID, Nombre y Edad**

![app](https://github.com/user-attachments/assets/a077c30f-0ece-46d8-b374-f7c1cc263bef)

### 4.Detalles del Empleado
*	Al seleccionar un empleado de la lista, la aplicación debe navegar a una nueva pantalla que muestre los detalles completos del empleado seleccionado.
*	La información del empleado será obtenida a través de un servicio REST. Para consultar los detalles de un empleado, se utilizará la siguiente URL, reemplazando {id} por el identificador del empleado correspondiente:
* 		GET /api/v1/employee/{id} (http://dummy.restapiexample.com/api/v1/employee/1)
*  En esta nueva pantalla, se deben mostrar los siguientes detalles del empleado: Nombre, Edad y Salario.
*  El color del texto tanto de la edad como del salario debe ser determinado mediante condicionales en el código.
#### **Edad:**
*	La edad debe mostrarse en verde si el empleado tiene más de 25 años pero menos de 35.
*	En cualquier otro caso, la edad debe mostrarse en rojo.
#### **Salario:**
*	El salario debe mostrarse en verde si es superior a 1000.
*	En cualquier otro caso, el salario debe mostrarse en rojo.

  ![image](https://github.com/user-attachments/assets/2ea15951-cd5d-4315-b37a-e79de6dce4cf)

## ENTREGABLES
* Sube tu código fuente de la aplicación a un repositorio público en GitHub o GitLab
* Instrucciones claras de instalación y ejecución de la aplicación
* Archivo build.gradle con todas las dependencias necesarias
* Envía el enlace del repositorio a los evaluadores
* Enviar capturas de pantalla del desarrollo e interacciones
* Notas de desarrollo que incluyan decisiones de diseño y tecnologías utilizadas

***
No te preocupes si no puedes completar todos los requisitos en el tiempo asignado. Se valorará el progreso y la calidad del trabajo realizado.

En Neology somos fieles creyentes de la transparencia, honestidad, crecimiento y aprendizaje por lo que agradecemos se pueda llevar a cabo la prueba sin ayuda de terceros o herramientas adicionales.

