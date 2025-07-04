# Obligatorio: Planificador de procesos

Para este obligatorio decidimos simular un planificador de procesos, donde podemos visualizar cómo funciona
cada uno de los diferentes métodos de planificación. En esta versión que hemos creado, decidimos devolver una 
lista con los tiempos de respuesta, espera y retorno de cada procesos, así es mucho más fácil verificar que funcionen
correctamente. Para un mejor entendimiento del código realizados, 
procedemos a explicar cada uno de los planificadores:

**Asignación de prioridades:**

Para esta simulación hermos decidio implementar una asiganción de prioridades *apropiativa*.
Este método simula la ejecución de un conjunto de procesos según prioridades, donde en cada unidad de tiempo 
se elige el proceso disponible con mayor prioridad (número de prioridad más bajo). Ejecuta una ráfaga por vez,
permitiendo así que un proceso más prioritario que llegue interrumpa al actual.

Primero se limpia la lista *planificadorLista* donde estarán los porcesos a ejecutarse. Mientras no se hayan completado 
todos los procesos, el método realiza lo siguiente: Primero se busca un proceso para ejecutar que ya haya llegado y que 
todavía tenga ráfagas por ejecutar, entre los candidatos se elige el de mayor prioridad. Una vez elegido, se ejecuta una
sola ráfaga (momentaneamente), se guarda el nombre del proceso y si al finalizar esa ráfaga el proceso ya no tiene más 
ráfagas, se incrementa el contador de procesos finalizados. Si no hay ningún proceso elegible (es decir, aún no han llegado 
o todos terminaron), se registra un " " en la lista. Finalmente se imprimen los tiempos de los procesos y el orden en el que 
se ejecutaron.


**FIFO:**

En la simulación del planificador FIFO *no apropiativo*, debemos ejecutar los procesos a medida que van llegando. Para
realizarlo, ordenamos la lista de procesos en orden de llegada y mientras hayan procesos en esta lista, se ejecutan por
completo. Finalmente se imprimen los tiempos de los procesos y el orden en el que se ejecutaron.


**Round Robin:**

En este caso simulampos el planificador de procesos donde cada proceso recibe un tiempo limitado de CPU (el quantum) 
y si no termina, vuelve al final de la cola. Además de limpiar el planificador e inicializar ciertas estructuras de control 
y almacenamiento de datos, primero se revisa si hay procesos que llegan al instante 0; aquellos que llegan se agregan a 
la *colaListos* y se eliminan de *procesosPorLlegar*. Mientras la cola de listos no esté vacía, se selecciona el siguiente 
proceso, obtenemos el nombre y las ráfagas por ejecutar, se ejecuta el procesos durante un tiempo máximo igual al quantum 
o menos si no le quedan tantas ráfagas. Por cada unidad se agrega el nombre al planificador, se avanza el reloj y si llegó
un proceso nuevo en ese tiempo, se lo agrega a la colaListos y se lo elimina de procesosPorLlegar. Si todavía le quedan ráfagas, 
se lo reagrega al final de la cola, esperando su próximo turno. Finalmente se imprimen los tiempos de los procesos y el orden 
en el que se ejecutaron.


**SJF:**

Este método simula un planificador que ejecuta siempre el proceso disponible con la ráfaga de CPU más corta. Una vez que
el proceso empieza, se ejecuta completo sin ser interrumpido, es decir, es *no apropiativo*. Comenzamos limpiando la lista 
del planificador e inicalizando las estructuras pertinentes. Siempre y cuando queden procesos sin ejecutar, Se busca el proceso 
disponible (ya llegado) con la ráfaga más corta. Si no hay ningún proceso que haya llegado aún, la CPU queda inactiva entonces 
se agrega " " a planificadorLista para indicar inactividad. Luego se avanza el tiempo y se reinicia la búsqueda. Al encontrar el 
proceso a ejecutar, se lo ejecuta de manera completa, es decir, durante todas las unidades de su ráfaga y cuando termina, se elimina 
de los procesos pendientes y se incrementa el contador de procesos ejecutados. Finalmente se imprimen los tiempos de los procesos y 
el orden en el que se ejecutaron.


**SRTF:**

Aquí se simula la ejecución de procesos según el algoritmo SRTF, donde en cada unidad de tiempo se selecciona el proceso disponible 
con la menor ráfaga restante. A diferencia del SJF, SRTF es *apropiativo*.
Como en todos los casos, limpiamos la lista planificador e inicializamos listas y contadores. Mientras no se hayan completado todos 
los procesos, el planificador sigue ejecutando. Para elegir el proceso a ejecutar, se recorre la lista de procesos y se filtran aquellos
que ya llegaron y todavía no terminaron. Entre los candidatos qeu cumplieron las condiciones, se elige el de menor ráfaga restante.
Si hay un empate (dos procesos con igual ráfaga), se sigue ejecutando el que ya estaba corriendo. Para poder evaluar si ha llegado otro
proceso de menor ráfaga, se ejecuta solo una unidad del proceso elegido (si sigue siendo el de menor rafaga, el bucle seguirá eligiendo el 
mismo). Finalmente se imprimen los tiempos de los procesos y el orden en el que se ejecutaron.


**Multicolas:**

Para este planificador se simula un planificador de procesos multinivel, donde los procesos se dividen en 3 colas según su prioridad (0, 1, 2)
y cada cola usa una política de planificación distinta: Prioridad 0: *Round Robin* (con quantum 2), Prioridad 1: *SJF* (no apropiativo) y 
Prioridad 2: *FIFO* (sin interrupciones). El planificador siempre elige primero la cola de mayor prioridad disponible, ejecuta un proceso 
de esa cola según su política, y luego vuelve a elegir.
Para esta simulación se crean tres colas de procesos, una para cada planificador. Mientras haya procesos que aún no llegaron o procesos que 
están en alguna cola, el planificador avanza una unidad de tiempo a la vez y hace lo siguiente:
Se revisan los procesos que llegaron en ese tiempo y se mueven a su cola correspondiente según la prioridad, Se elige el primer proceso 
disponible de la cola de mayor prioridad no vacía y según que cola sea, se realiza la misma implementación que se detalló anteriormente en cada
caso (FIFO, Round Robin y SJF). Finalmente se imprimen los tiempos de los procesos y el orden en el que se ejecutaron.


**Detalles de implementación:**

- Método *generarProcesoRandom* en Main: Además de los ejemplos que hemos colocado en el Main para verificar el correcto funcionamiento del programa, creamos
este método que genera procesos con rafagas y prioridades distintas cada vez, así verificamos que funcione en la mayor cantidad de casos posibles.
- Método *ejecutar* en Proceso: La manera que tenemos de simular una ejecución en este proyecto es creando este método que simplemente disminuye la ráfaga.
- Atributos *rafaga* y *rafagaUltima* en Proceso: Debido a que decidimos calcular los tiempos de cada proceso, necesitabamos tener el valor de laráfaga de
dicho proceso; sin embargo, ya que los tiempos es algo que se calcula una vez ejecutados todos los procesos, ya que el método ejecutar disminuía la rafaga, 
no podiamos acceder al valor inicial. Por eso creamos un valor de ráfaga inmutable (rafaga) y otro con el que simulamos la ejecución (rafagaUltima).
- Atributo *planificadorLista* en Planificador: Para poder crear el diagrama del orden de los procesos debimos guardar en una lista los nombres de los mismos
y el orden en el que aparecen. En cada método se limpia esta lista por si quedaron los procesos de otro planificador que la haya usado. Decidimos que todos
utilizaran la misma para evitar generar una en cada planificador y así ahorrar espacio de memoria.
- Método *bubbleSortPorLlegada* en Planificador: Para ordenar los procesos por orden de llegada decidimos utilizar un método de ordenamiento, que aprendimos en
la asignatura algoritmos y bases de datos, llamado BubbleSort. Promete ser bastante eficiente para los valores con los que trabajamos.
