🚚 Speed Fast App
---
Actividad sumativa 2 (Semana 5)

## 📖 Descripción

Este sistema es un motor de simulación de despacho de pedidos de alta eficiencia y rendimiento, diseñado bajo el patrón Productor-Consumidor ("a medias") utilizando técnicas avanzadas de concurrencia nativa en Java. El software gestiona una piscina común de carga de pedidos donde múltiples hilos trabajadores (Repartidores) compiten dinámicamente por la distribución de las tareas, optimizando el uso de recursos y mitigando condiciones de carrera.

---

## 🚀 Características Principales

* Concurrencia Segura (Thread-Safe): Implementación de la interfaz BlockingQueue (a través de la clase concreta LinkedBlockingQueue) para la administración atómica de recursos comunes, garantizando la eliminación del "doble retiro" sin incurrir en penalizaciones de rendimiento por sobre-sincronización (synchronized overhead).
* Hardening y Ciberseguridad: Centralización del control de flujos de error mediante un Global Uncaught Exception Handler, mitigando la vulnerabilidad de Fuga de Información (Information Disclosure) al ocultar Stack Traces técnicos al usuario final.
* Programación Defensiva: Validación temprana (Fail-Fast) en todos los constructores y setters mediante restricciones lógicas de negocio y herramientas nativas de Java (Objects.requireNonNull).
* Simulación Desfasada: Simulación dinámica del ciclo de transporte mediante pausas aleatorias escalonadas independientes (ThreadLocalRandom), logrando una salida fluida en consola que imita las operaciones logísticas reales.

---

## 📁 Estructura del Proyecto

```text
speed-fast-semana5-v3/                  
└── src/
    └── main/
        └── java/
            └── cl/
                └── duoc/
                    └── speedfast/
                        │
                        ├── Main.java                 # Director de orquesta principal y escudo global de errores
                        │
                        ├── model
                        │   ├── Pedido.java           # Entidad del modelo con ID inmutable y validaciones defensivas
                        │   └── EstadoPedido.java     # Enum estricto de control de ciclo de vida (PENDIENTE, EN_REPARTO, ENTREGADO)
                        │
                        └── service
                            ├── ZonaDeCarga.java      # Recurso compartido concurrente (Encapsula la BlockingQueue)
                            └── Repartidor.java       # Hilo Runnable de procesamiento logístico y control de interrupciones
```

---

## 🛠️ Instrucciones para clonar y ejecutar

Requisitos del sistema:

* **JDK:** Java 25 (LTS) o superior

1. Clonar el repositorio desde la terminal de la computadora o IDE:  
   git clone https://github.com/alonsobonansco/speed-fast-semana5-v3.git
2. Ir a File →️ Open y seleccionar la carpeta raíz del proyecto (la carpeta que contiene el archivo pom.xml).
3. Ejecutar el `Main` desde su clase en el paquete raíz `cl.duoc.speedfast`

---

## 👤 Autor

Alonso Bonansco Vergara  
Desarrollo Orientado a Objetos II - 004A  
Analista Programador Computacional
