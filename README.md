# CircuitFlow

CircuitFlow is a desktop application that solves DC electrical circuits built
from resistors and voltage sources, using **Modified Nodal Analysis (MNA)**.

> **Work in progress.** The circuit model is done and covered by unit tests;
> validation and the solver are next.

## Planned features

- Build circuits from resistors, DC voltage sources and a ground node
- Solve node voltages, branch currents and power
- A clear error message when a circuit cannot be solved
- Save and open circuits as JSON
- A desktop editor with a results panel

## Progress

- [x] Project setup and CI
- [x] Node, resistor and voltage source models
- [x] Circuit: nodes, components and ground
- [ ] Circuit validation (ground and connectivity)
- [ ] MNA matrix assembly
- [ ] Linear solver and results
- [ ] JSON save and load
- [ ] Editor and results panel
- [ ] Screenshots and demo

## Tech stack

Java 21 · JavaFX · Gradle · JUnit 5 · GitHub Actions

## Getting started

Requires JDK 21.

```bash
./gradlew run     # start the application
./gradlew test    # run the unit tests
```

## Project structure

```text
app/src/main/java/.../model/   circuit model: nodes, components, circuit
app/src/test/java/.../model/   unit tests for the model
examples/                      sample circuits in JSON
```

## License

MIT
