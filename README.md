# Diseño de Microservicio en Kotlin

La empresa necesita implementar un microservicio que maneje la gestión de usuarios en un sistema de banca en línea. Este microservicio debe registrar, actualizar y eliminar usuarios, asegurando la consistencia de los datos y manejando posibles errores de manera eficiente. El sistema debe ser capaz de procesar un mínimo de 1 000 transacciones por segundo con un tiempo de respuesta promedio de 50 milisegundos.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | microservicios en kotlin |
| **Nivel** | junior-l2 |
| **Tipo** | theoretical |
| **Tiempo estimado** | 4 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: Un IDE o editor de código.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Verifica que el proyecto arranca sin errores.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Exploración del Dominio

**Objetivo:** Identificar y entender los actores, fuentes y sumideros involucrados en la gestión de usuarios.

**Tiempo estimado:** 1 hora

**Instrucciones:**

- Enumera los actores involucrados en el proceso de gestión de usuarios (ej. originador de créditos, motor antifraude).
- Identifica las fuentes y sumideros de datos (ej. base de datos de usuarios, sistema de autenticación).
- Determina las reglas de negocio y validaciones necesarias (ej. nombres únicos, edad mínima).

**Entregable:** Mapa conceptual del dominio de gestión de usuarios.

<details>
<summary>Pistas de conocimiento</summary>

- Considera los posibles edge cases y cómo manejarlos (ej. usuario duplicado, datos faltantes).
- Piensa en las dependencias entre los diferentes componentes del sistema.

</details>

### Fase 2: Evaluación de Decisiones de Diseño

**Objetivo:** Evaluar y seleccionar la mejor opción de diseño para el microservicio de gestión de usuarios.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Analiza las diferentes opciones de diseño para el microservicio (ej. arquitectura monolítica vs microservicios).
- Evalúa los pros y contras de cada opción, considerando la escalabilidad, mantenibilidad y seguridad.
- Selecciona la opción que mejor se ajuste a las necesidades del sistema.

**Entregable:** Documento de decisión de diseño con pros y contras de cada opción y la opción seleccionada.

<details>
<summary>Pistas de conocimiento</summary>

- Considera los trade-offs entre consistencia y disponibilidad.
- Piensa en cómo manejar las posibles fallas del sistema (ej. timeouts, errores de red).

</details>

### Fase 3: Comunicación de la Decisión

**Objetivo:** Comunicar la decisión de diseño seleccionada a diferentes audiencias.

**Tiempo estimado:** 1 hora

**Instrucciones:**

- Prepara una presentación para comunicar la decisión de diseño a la audiencia técnica (ej. desarrolladores, arquitectos).
- Prepara un informe para comunicar la decisión de diseño a la audiencia de negocio (ej. gerentes, stakeholders).
- Asegúrate de que ambas audiencias entiendan los beneficios y riesgos de la decisión seleccionada.

**Entregable:** Presentación y informe de la decisión de diseño.

<details>
<summary>Pistas de conocimiento</summary>

- Considera el lenguaje y los conceptos adecuados para cada audiencia.
- Asegúrate de que la comunicación sea clara y concisa.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es un microservicio y cuáles son sus principales características?
- **paraQueSirve**: ¿Para qué sirve un microservicio en el contexto de la gestión de usuarios en un sistema de banca en línea?
- **comoSeUsa**: ¿Cómo se usa un microservicio para manejar la consistencia de los datos y los posibles errores?
- **erroresComunes**: ¿Cuáles son los errores comunes al diseñar y implementar un microservicio y cómo se pueden evitar?
- **queDecisionesImplica**: ¿Qué decisiones de diseño implica la implementación de un microservicio para la gestión de usuarios?

## Criterios de Evaluacion

- Identificación correcta de actores, fuentes y sumideros en el dominio de gestión de usuarios.
- Evaluación y selección adecuada de la opción de diseño para el microservicio.
- Comunicación clara y efectiva de la decisión de diseño a diferentes audiencias.

---

*Reto generado automaticamente por Challenge Generator - Pragma*
