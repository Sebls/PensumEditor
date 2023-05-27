package com.pensumeditor.pensums;

import com.pensumeditor.data.PositionSubject;
import com.pensumeditor.data.Subject;

import java.util.ArrayList;

public class IngSistemas {
    public IngSistemas() {

    }
    public ArrayList<PositionSubject> GeneratePensum() {

        ArrayList<PositionSubject> SubjectArray = new ArrayList<>();

        SubjectArray.add(new PositionSubject(0, 1, new Subject(1000004, "Cálculo Diferencial", 4, "Matemáticas", "Matemáticas Básicas", "COMPONENTE DE FUNDAMENTACIÓN") ) );
        SubjectArray.add(new PositionSubject(0, 2, new Subject(2025975, "Introducción a la Ingeniería de Sistemas y Computación", 3, "Contexto Profesional y Proyectos de Ingeniería", "-", "COMPONENTE DE FORMACIÓN DISCIPLINAR O PROFESIONAL") ) );
        SubjectArray.add(new PositionSubject(0, 3, new Subject(2015734, "Programación de Computadores", 3, "Métodos y Tecnologías de Software", "-", "COMPONENTE DE FORMACIÓN DISCIPLINAR O PROFESIONAL") ) );
        SubjectArray.add(new PositionSubject(0, 5, new Subject(2016703, "Pensamiento Sistémico", 3, "Modelos, Sistemas, Optimización y Simulación", "", "COMPONENTE DE FORMACIÓN DISCIPLINAR O PROFESIONAL") ) );
        SubjectArray.add(new PositionSubject(1, 0, new Subject(1000019, "Fundamentos de Mecánica", 4, "Física", "Cálculo Diferencial o Cálculo Diferencial en una Variable", "COMPONENTE DE FUNDAMENTACIÓN") ) );
        SubjectArray.add(new PositionSubject(1, 1, new Subject(1000005, "Cálculo Integral", 4, "Matemáticas", "Cálculo Diferencial o Cálculo Diferencial en una Variable", "COMPONENTE DE FUNDAMENTACIÓN") ) );
        SubjectArray.add(new PositionSubject(1, 2, new Subject(1000003, "Álgebra Lineal", 4, "Matemáticas", "Cálculo Diferencial o Cálculo Diferencial en una Variable", "COMPONENTE DE FUNDAMENTACIÓN") ) );
        SubjectArray.add(new PositionSubject(1, 3, new Subject(2016375, "Programación Orientada a Objetos", 3, "Métodos y Tecnologías de Software", "Programación de Computadores o Introducción a las ciencias de la computación.", "COMPONENTE DE FORMACIÓN DISCIPLINAR O PROFESIONAL") ) );
        SubjectArray.add(new PositionSubject(2, 0, new Subject(1000017, "Fundamentos de Electricidad y Magnetismo", 4, "Física", "Cálculo Integral o Cálculo integral en una variable ; y Fundamentos de Mecánica", "COMPONENTE DE FUNDAMENTACIÓN") ) );
        SubjectArray.add(new PositionSubject(2, 1, new Subject(1000006, "Cálculo en Varias Variables", 4, "Matemáticas", "Cálculo Integral o Cálculo integral en una variable", "COMPONENTE DE FUNDAMENTACIÓN") ) );
        SubjectArray.add(new PositionSubject(2, 2, new Subject(2025963, "Matemáticas Discretas I", 4, "Ciencias de la Computación", "Álgebra Lineal o Álgebra Lineal Básica", "COMPONENTE DE FUNDAMENTACIÓN") ) );
        SubjectArray.add(new PositionSubject(2, 4, new Subject(2016353, "Bases de Datos", 3, "Infraestructura Computacional, de Comunicaciones y de Información", "Programación Orientada a Objetos", "COMPONENTE DE FORMACIÓN DISCIPLINAR O PROFESIONAL") ) );
        SubjectArray.add(new PositionSubject(2, 5, new Subject(2016698, "Elementos de Computadores", 3, "Infraestructura Computacional, de Comunicaciones y de Información", "Introducción a la Ingeniería de Sistemas y Computación", "COMPONENTE DE FORMACIÓN DISCIPLINAR O PROFESIONAL") ) );
        SubjectArray.add(new PositionSubject(3, 0, new Subject(1000013, "Probabilidad y Estadística Fundamental", 3, "Probabilidad y Estadística", "Cálculo Integral o Cálculo integral en una variable", "COMPONENTE DE FUNDAMENTACIÓN") ) );
        SubjectArray.add(new PositionSubject(3, 1, new Subject(2015703, "Ingeniería Económica", 3, "Ciencias Económicas y Administrativas", "Cálculo Integral o Cálculo Integral en una variable", "COMPONENTE DE FUNDAMENTACIÓN") ) );
        SubjectArray.add(new PositionSubject(3, 2, new Subject(2025964, "Matemáticas Discretas II", 4, "Ciencias de la Computación", "Matemáticas Discretas I o Teoría de Grafos", "COMPONENTE DE FUNDAMENTACIÓN") ) );
        SubjectArray.add(new PositionSubject(3, 3, new Subject(2016699, "Estructuras de Datos", 3, "Métodos y Tecnologías de Software", "Programación Orientada a Objetos", "COMPONENTE DE FORMACIÓN DISCIPLINAR O PROFESIONAL") ) );
        SubjectArray.add(new PositionSubject(3, 5, new Subject(2016697, "Arquitectura de Computadores", 3, "Infraestructura Computacional, de Comunicaciones y de Información", "Elementos de Computadores o Electrónica Digital I", "COMPONENTE DE FORMACIÓN DISCIPLINAR O PROFESIONAL") ) );
        SubjectArray.add(new PositionSubject(4, 0, new Subject(2025970, "Modelos y Simulación", 3, "Modelos, Sistemas, Optimización y Simulación", "Cálculo en Varias Variables o Cálculo Vectorial; y, Matemáticas Discretas II o Ecuaciones en Diferencias Finitas y Sistemas Dinámicos; y, Probabilidad y Estadística Fundamental o  Probabilidad; y , Programación Orientada a Objetos", "COMPONENTE DE FORMACIÓN DISCIPLINAR O PROFESIONAL") ) );
        SubjectArray.add(new PositionSubject(4, 1, new Subject(2015702, "Gerencia y Gestión de Proyectos", 3, "Ciencias Económicas y Administrativas", "Ingeniería Económica o Ingeniería Económica y Análisis de Riesgo o Modelos Económicos Computacionales", "COMPONENTE DE FUNDAMENTACIÓN") ) );
        SubjectArray.add(new PositionSubject(4, 2, new Subject(2025967, "Redes de Computadores", 3, "Infraestructura Computacional, de Comunicaciones y de Información", "Fundamentos de Electricidad y Magnetismo; y, Estructuras de Datos; y, Arquitectura de Computadores", "COMPONENTE DE FUNDAMENTACIÓN") ) );
        SubjectArray.add(new PositionSubject(4, 3, new Subject(2016701, "Ingeniería de Software I", 3, "Métodos y Tecnologías de Software", "Pensamiento Sistémico; y, Estructuras de Datos; y, Bases de Datos o Análisis de Bases de Datos;", "COMPONENTE DE FUNDAMENTACIÓN") ) );
        SubjectArray.add(new PositionSubject(4, 4, new Subject(2015174, "Introducción a la Teoría de la Computación", 4, "Ciencias de la Computación", "Matemáticas Discretas I o Teoría de Grafos", "COMPONENTE DE FUNDAMENTACIÓN") ) );
        SubjectArray.add(new PositionSubject(5, 0, new Subject(2025971, "Optimización", 3, "Modelos, Sistemas, Optimización y Simulación", "Modelos y Simulación o Modelos Matemáticos", "COMPONENTE DE FUNDAMENTACIÓN") ) );
        SubjectArray.add(new PositionSubject(5, 1, new Subject(2025982, "Sistemas de Información", 3, "Infraestructura Computacional, de Comunicaciones y de Información", "Bases de Datos o Análisis de Bases de Datos; y, Gerencia y Gestión de Proyectos o Diseño, Gestión y Evaluación de Proyectos; y, Pensamiento Sistémico", "COMPONENTE DE FUNDAMENTACIÓN") ) );
        SubjectArray.add(new PositionSubject(5, 2, new Subject(2015970, "Métodos Numéricos", 3, "Ciencias de la Computación", "Cálculo en Varias Variables o Cálculo Vectorial", "COMPONENTE DE FUNDAMENTACIÓN") ) );
        SubjectArray.add(new PositionSubject(5, 3, new Subject(2016702, "Ingeniería de Software II", 3, "Métodos y Tecnologías de Software", "Ingeniería de Software I; y, Redes de Computadores", "COMPONENTE DE FUNDAMENTACIÓN") ) );
        SubjectArray.add(new PositionSubject(5, 4, new Subject(2016696, "Algoritmos", 3, "Ciencias de la Computación", "Estructuras de Datos; y, Matemáticas Discretas I o Teoría de Grafos; y, Matemáticas Discretas II o Ecuaciones en Diferencias y Sistemas Dinámicos", "COMPONENTE DE FUNDAMENTACIÓN") ) );
        SubjectArray.add(new PositionSubject(5, 5, new Subject(2016707, "Sistemas Operativos", 3, "Infraestructura Computacional, de Comunicaciones y de Información", "Arquitectura de Computadores", "COMPONENTE DE FUNDAMENTACIÓN") ) );
        SubjectArray.add(new PositionSubject(6, 0, new Subject(2025969, "Modelos Estocásticos y Simulación en Computación y Comunicaciones", 3, "Modelos, Sistemas, Optimización y Simulación", "Optimización o Introducción a la Optimización", "COMPONENTE DE FUNDAMENTACIÓN") ) );
        SubjectArray.add(new PositionSubject(6, 1, new Subject(2025983, "Arquitectura de Infraestructura y gobierno de TICs", 3, "Infraestructura Computacional, de Comunicaciones y de Información", "Ingeniería de Software II y Sistemas de Información", "COMPONENTE DE FUNDAMENTACIÓN") ) );
        SubjectArray.add(new PositionSubject(6, 2, new Subject(2025994, "Teoría de la Información y Sistemas de Comunicaciones", 3, "Infraestructura Computacional, de Comunicaciones y de Información", "Redes de Computadores; y Probabilidad y Estadística Fundamental o Probabilidad", "COMPONENTE DE FUNDAMENTACIÓN") ) );
        SubjectArray.add(new PositionSubject(6, 3, new Subject(2016716, "Arquitectura de Software", 3, "Métodos y Tecnologías de Software", "Ingeniería de Software II", "COMPONENTE DE FUNDAMENTACIÓN") ) );
        SubjectArray.add(new PositionSubject(6, 4, new Subject(2025995, "Introducción a los Sistemas Inteligentes", 3, "Sistemas Inteligentes", "Algoritmos", "COMPONENTE DE FUNDAMENTACIÓN") ) );
        SubjectArray.add(new PositionSubject(6, 5, new Subject(2025966, "Lenguajes de Programación", 3, "Métodos y Tecnologías de Software", "Estructuras de Datos; e, Introducción a la Teoría de la Computación", "COMPONENTE DE FUNDAMENTACIÓN") ) );
        SubjectArray.add(new PositionSubject(7, 0, new Subject(2024045, "Taller de Proyectos Interdisciplinarios", 3, "Contexto Profesional y Proyectos de Ingeniería", "Haber aprobado 40 créditos del Componente de Formación Disciplinar o Profesional; y, Gerencia y Gestión de Proyectos o Diseño, Gestión y Evaluación de Proyectos", "COMPONENTE DE FUNDAMENTACIÓN") ) );
        SubjectArray.add(new PositionSubject(7, 1, new Subject(2016722, "Computación Paralela y Distribuida", 3, "Infraestructura Computacional, de Comunicaciones y de Información", "Algoritmos", "COMPONENTE DE FUNDAMENTACIÓN") ) );
        SubjectArray.add(new PositionSubject(7, 2, new Subject(2025972, "Introducción a la Criptografía y a la Seguridad de la Información", 3, "Infraestructura Computacional, de Comunicaciones y de Información", "Algoritmos", "COMPONENTE DE FUNDAMENTACIÓN") ) );
        SubjectArray.add(new PositionSubject(7, 3, new Subject(2025960, "Computación Visual", 3, "Computación Visual", "Algoritmos", "COMPONENTE DE FUNDAMENTACIÓN") ) );
        SubjectArray.add(new PositionSubject(7, 4, new Subject(1, "Electiva", 3, "Libre elección", "-", "COMPONENTE DE LIBRE ELECCIÓN") ) );
        SubjectArray.add(new PositionSubject(7, 5, new Subject(1, "Electiva", 2, "Libre elección", "-", "COMPONENTE DE LIBRE ELECCIÓN") ) );
        SubjectArray.add(new PositionSubject(8, 2, new Subject(1, "Electiva", 4, "Libre elección", "-", "COMPONENTE DE LIBRE ELECCIÓN") ) );
        SubjectArray.add(new PositionSubject(8, 3, new Subject(1, "Electiva", 4, "Libre elección", "-", "COMPONENTE DE LIBRE ELECCIÓN") ) );
        SubjectArray.add(new PositionSubject(8, 4, new Subject(1, "Electiva", 4, "Libre elección", "-", "COMPONENTE DE LIBRE ELECCIÓN") ) );
        SubjectArray.add(new PositionSubject(8, 5, new Subject(1, "Electiva", 4, "Libre elección", "-", "COMPONENTE DE LIBRE ELECCIÓN") ) );
        SubjectArray.add(new PositionSubject(9, 2, new Subject(1, "Electiva", 4, "Libre elección", "-", "COMPONENTE DE LIBRE ELECCIÓN") ) );
        SubjectArray.add(new PositionSubject(9, 3, new Subject(1, "Electiva", 4, "Libre elección", "-", "COMPONENTE DE LIBRE ELECCIÓN") ) );
        SubjectArray.add(new PositionSubject(9, 4, new Subject(1, "Electiva", 4, "Libre elección", "-", "COMPONENTE DE LIBRE ELECCIÓN") ) );
        SubjectArray.add(new PositionSubject(9, 5, new Subject(2025973, "Trabajo de Grado - Práctica de Extensión", 6, "TRABAJO DE GRADO", "Haber aprobado 60 créditos del Componente de Formación Disciplinar o Profesional", "COMPONENTE DE FUNDAMENTACIÓN") ) );

        return SubjectArray;
    }
}

