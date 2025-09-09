# Pokemon POO

## Objetivos

- Modelar una jerarquía con una clase abstracta y subclases concretas
- Aplicar polimorfismo al calcular daño según el tipo de atacante
- Implementar sobrecarga de métodos (mismo nombre, formas distintas)
- Asegurar validaciones básicas y buen diseño (SRP/LSP)

## Enunciado (resumen)

Modelar un sistema mínimo de combate con tres tipos: **Fuego**, **Agua** y **Planta**.
Cada Pokemon tiene nombre, nivel, hp (salud) y un tipo. Deben poder atacar a otro Pokemon.
El daño base se ajusta por efectividad de tipos:

- **Fuego** > **Planta** (x1.5)
- **Agua** > **Fuego** (x1.5)
- **Planta** > **Agua** (x1.5)
- Mismo tipo o no efectivo: x1.0

Además incluir dos variantes de ataque (sobrecarga): 
1. Atacar (Pokemon objetivo) (normal)
2. Atacar (Pokemon objetivo, String clima) donde: 
   - "soleado" potencia **Fuego** (x1.2) y debilita **Agua** (x0.8)
   - "lluvia" potencia **Agua** (x1.2) y debilita **Fuego** (x0.8)
   - otros climas: neutro (x1.0)

Bonus opcional: Interfaz evolucionable con boolean puedeEvolucionar()

## Entregables
1. Código JAVA en un único archivo .java (sin packages)
2. Prueba en main con al menos 3 Pokemon distintos
3. Demostrar polimorfismo (misma llamada, distinto resultado)
4. Usar correctamente sobrecarga de métodos/constructores
5. Validaciones simples y mensajes claros
