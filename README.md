# Aplicación de Gestión de Tareas

## Objetivo

La aplicación de Gestión de Tareas está diseñada para ayudar a los usuarios a gestionar sus tareas de manera eficiente.
Los usuarios pueden agregar, ver, editar y eliminar tareas.
Las tareas pueden marcarse como completadas o pendientes, y se categorizan según sus niveles de prioridad (Baja, Normal, Alta).
La aplicación asegura que todos los detalles de la tarea estén completos antes de guardarla y proporciona una interfaz amigable para la gestión de tareas.

## Descripción de Clases Java

### `ActividadRegistro.java`

Maneja el registro de nuevas tareas. Asegura que todos los campos estén completos antes de guardar y utiliza un selector de fecha para elegir fechas.

### `ActividadDetalles.java`

Muestra los detalles de una tarea seleccionada. La prioridad se codifica por colores según su nivel (Verde para Baja, Amarillo para Normal, Rojo para Alta).

### `ActividadTareasPendientes.java`

Muestra la lista de tareas pendientes. Permite a los usuarios agregar nuevas tareas, marcar tareas como completadas y eliminar tareas.

### `ActividadTareasHechas.java`

Muestra la lista de tareas completadas. Permite a los usuarios marcar tareas como pendientes y eliminar tareas.

### `AdaptadorTarea.java`

Clase adaptadora para gestionar la lista de tareas en un `RecyclerView`. Maneja los eventos de clic para marcar tareas como completadas/pendientes y eliminar tareas.

### `RepositorioTarea.java`

Clase Singleton que gestiona el almacenamiento y recuperación de tareas. Utiliza `SharedPreferences` para persistir las tareas.

### `Tarea.java`

Clase modelo que representa una tarea con atributos como nombre, descripción, fecha, prioridad, precio y estado de finalización.

### Descripción de Archivos XML

- `actividad_registro.xml`: Diseño para la pantalla de registro de tareas.
- `actividad_detalles.xml`: Diseño para la pantalla de detalles de tareas.
- `actividad_tareas_pendientes.xml`: Diseño para la pantalla de tareas pendientes.
- `actividad_tareas_hechas.xml`: Diseño para la pantalla de tareas completadas.
- `item_tarea.xml`: Diseño para los elementos individuales de tarea en el `RecyclerView`.

### Link al repositorio: https://github.com/Samuu10/ExamenEjercicio3.git
