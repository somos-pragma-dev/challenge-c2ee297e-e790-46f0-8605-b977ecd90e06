# Prompt para Mejorar el Codigo Base

Copia y pega el siguiente contenido completo en un asistente de IA (Claude, ChatGPT, etc.)
para obtener un ZIP con el proyecto corregido y listo para compilar.

---

```
Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación o descripciones sin código, genera los archivos
correspondientes sin aplicar análisis de compilación
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:
// === ARCHIVO: src/main/kotlin/com/bank/userservice/api/UserController.kt ===
package com.bank.userservice.api

import com.bank.userservice.domain.User
import com.bank.userservice.infrastructure.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/users", produces = [MediaType.APPLICATION_JSON_VALUE])
class UserController(private val userRepository: UserRepository) {

    @PostMapping
    fun createUser(@RequestBody user: User): Mono<ResponseEntity<User>> {
        return userRepository.save(user)
           .map { ResponseEntity.status(HttpStatus.CREATED).body(it) }
    }

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: String): Mono<ResponseEntity<User>> {
        return userRepository.findById(id)
           .map { ResponseEntity.ok(it) }
           .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
    }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: String, @RequestBody user: User): Mono<ResponseEntity<User>> {
        return userRepository.findById(id)
           .flatMap { userRepository.save(user.copy(id = it.id)) }
           .map { ResponseEntity.ok(it) }
           .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: String): Mono<ResponseEntity<Void>> {
        return userRepository.findById(id)
           .flatMap { userRepository.deleteById(id) }
           .thenReturn(ResponseEntity.noContent().build())
           .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
    }
}

// === ARCHIVO: src/main/kotlin/com/bank/userservice/domain/User.kt ===
package com.bank.userservice.domain

data class User(
    val id: String? = null,
    val name: String,
    val age: Int
)

// === ARCHIVO: src/main/kotlin/com/bank/userservice/infrastructure/UserRepository.kt ===
package com.bank.userservice.infrastructure

import com.bank.userservice.domain.User
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface UserRepository {
    fun save(user: User): Mono<User>
    fun findById(id: String): Mono<User>
    fun deleteById(id: String): Mono<Void>
}

// === ARCHIVO: src/main/resources/config/application.yml ===
spring:
  application:
    name: user-service
  data:
    mongodb:
      uri: mongodb://localhost:27017/user-service

// === ARCHIVO: src/test/kotlin/com/bank/userservice/UserControllerTest.kt ===
package com.bank.userservice

import com.bank.userservice.api.UserController
import com.bank.userservice.domain.User
import com.bank.userservice.infrastructure.UserRepository
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono

@WebFluxTest(UserController::class)
class UserControllerTest(
    @Autowired private val webTestClient: WebTestClient,
    @MockBean private val userRepository: UserRepository
) {

    @Test
    fun `create user returns 201 and user`() {
        val user = User(name = "John Doe", age = 30)
        whenever(userRepository.save(user)).thenReturn(Mono.just(user))

        webTestClient.post()
           .uri("/users")
           .contentType(MediaType.APPLICATION_JSON)
           .bodyValue(user)
           .exchange()
           .expectStatus().isCreated
           .expectBody(User::class.java).isEqualTo(user)
    }

    @Test
    fun `get user returns 200 and user`() {
        val user = User(id = "1", name = "John Doe", age = 30)
        whenever(userRepository.findById("1")).thenReturn(Mono.just(user))

        webTestClient.get()
           .uri("/users/1")
           .exchange()
           .expectStatus().isOk
           .expectBody(User::class.java).isEqualTo(user)
    }

    @Test
    fun `update user returns 200 and updated user`() {
        val user = User(id = "1", name = "John Doe", age = 30)
        whenever(userRepository.findById("1")).thenReturn(Mono.just(user))
        whenever(userRepository.save(user)).thenReturn(Mono.just(user))

        webTestClient.put()
           .uri("/users/1")
           .contentType(MediaType.APPLICATION_JSON)
           .bodyValue(user)
           .exchange()
           .expectStatus().isOk
           .expectBody(User::class.java).isEqualTo(user)
    }

    @Test
    fun `delete user returns 204`() {
        whenever(userRepository.findById("1")).thenReturn(Mono.just(User(id = "1", name = "John Doe", age = 30)))
        whenever(userRepository.deleteById("1")).thenReturn(Mono.empty())

        webTestClient.delete()
           .uri("/users/1")
           .exchange()
           .expectStatus().isNoContent
    }
}

// === ARCHIVO: src/main/kotlin/com/bank/userservice/infrastructure/HttpClient.kt ===
package com.bank.userservice.infrastructure

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class HttpClient(builder: WebClient.Builder) {
    private val webClient: WebClient = builder.build()

    fun get(url: String): Mono<String> {
        return webClient.get()
           .uri(url)
           .retrieve()
           .bodyToMono(String::class.java)
    }
}

// === ARCHIVO: src/main/kotlin/com/bank/userservice/infrastructure/CircuitBreakerConfig.kt ===
package com.bank.userservice.infrastructure

import io.github.resilience4j.circuitbreaker.CircuitBreaker
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CircuitBreakerConfig {

    @Bean
    fun circuitBreaker(): CircuitBreaker {
        return CircuitBreaker.of("user-service", CircuitBreakerConfig.ofDefaults())
    }
}

```
