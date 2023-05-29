package com.pensumeditor.gui;

import com.pensumeditor.data.PositionSubject;
import com.pensumeditor.data.Subject;
import com.pensumeditor.datastructures.linear.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SubjectSelectorController implements Initializable {

    @FXML
    TableColumn Codigo;
    @FXML
    TableColumn Nombre;
    @FXML
    TableColumn Creditos;
    @FXML
    TableColumn Grupo;
    @FXML
    TableColumn Prerequisitos;
    @FXML
    TableColumn Componente;
    @FXML
    TableView SubjectTable;

    public Object selectedSubject;
    public int[] position;

    private ArrayList<PositionSubject> SubjectsArray;
    private int semesterNumber;

    public void loadSubjectsArray(ArrayList<PositionSubject> SubjectsArray, int semesterNumber) {
        this.SubjectsArray = SubjectsArray;
        this.semesterNumber = semesterNumber;
    }

    @FXML
    public void SelectedSubject(MouseEvent event) {
        if( event.getClickCount() == 2) {
            this.selectedSubject = SubjectTable.getSelectionModel().getSelectedItem();
            Stage stage = (Stage) SubjectTable.getScene().getWindow();
            stage.close();
        }
    }

    public int[] getPosition() {
        return this.position;
    }

    private Scene scene;

    public void openPositionSelector() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("PositionSelector.fxml"));
            AnchorPane positionSelector = fxmlLoader.load();
            PositionSelectorController psc = fxmlLoader.getController();
            psc.loadSubjectsArray(SubjectsArray, semesterNumber);
            psc.loadFreeGridPane();
            scene = new Scene(positionSelector, 600, 400);
            Stage stage = new Stage();
            stage.setTitle("Position Selector");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.showAndWait();
            this.position = psc.getPosition();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Object getSelectedSubject() {
        return this.selectedSubject;
    }



    public void loadSubjects(ArrayList<Subject> subjects) {
        Codigo.setCellValueFactory(new PropertyValueFactory<>("code"));
        Nombre.setCellValueFactory(new PropertyValueFactory<>("name"));
        Creditos.setCellValueFactory(new PropertyValueFactory<>("credits"));
        Grupo.setCellValueFactory(new PropertyValueFactory<>("group"));
        Prerequisitos.setCellValueFactory(new PropertyValueFactory<>("prerequisite"));
        Componente.setCellValueFactory(new PropertyValueFactory<>("component"));
        for (int i=0; i<subjects.getSize(); i++) {
            SubjectTable.getItems().add(subjects.get(i));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ArrayList<Subject> Subjects = new ArrayList<>();

        Subjects.add( new Subject(1000004, "Cálculo Diferencial", 4, "Matemáticas", "Matemáticas Básicas", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2016377, "Cálculo Diferencial en una Variable", 4, "Matemáticas", "Matemáticas Básicas", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(1000005, "Cálculo Integral", 4, "Matemáticas", "Cálculo Diferencial o Cálculo Diferencial en una Variable", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2015556, "Cálculo integral en una variable", 4, "Matemáticas", "Cálculo Diferencial o Cálculo Diferencial en una Variable", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(1000006, "Cálculo en Varias Variables", 4, "Matemáticas", "Cálculo Integral o Cálculo integral en una variable", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2015162, "Cálculo Vectorial", 4, "Matemáticas", "Cálculo Integral o Cálculo integral en una variable", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(1000003, "Álgebra Lineal", 4, "Matemáticas", "Cálculo Diferencial o Cálculo Diferencial en una Variable", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2015555, "Álgebra Lineal Básica", 4, "Matemáticas", "Cálculo Diferencial o Cálculo Diferencial en una Variable", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(1000013, "Probabilidad y Estadística Fundamental", 3, "Probabilidad y Estadística", "Cálculo Integral o Cálculo integral en una variable", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2027877, "Probabilidad Fundamental", 4, "Probabilidad y Estadística", "Cálculo Integral o Cálculo integral en una variable", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2015178, "Probabilidad", 4, "Probabilidad y Estadística", "Cálculo Integral o Cálculo integral en una variable", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(1000019, "Fundamentos de Mecánica", 4, "Física", "Cálculo Diferencial o Cálculo Diferencial en una Variable", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(1000017, "Fundamentos de Electricidad y Magnetismo", 4, "Física", "Cálculo Integral o Cálculo integral en una variable ; y Fundamentos de Mecánica", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2015174, "Introducción a la Teoría de la Computación", 4, "Ciencias de la Computación", "Matemáticas Discretas I o Teoría de Grafos", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2016696, "Algoritmos", 3, "Ciencias de la Computación", "Estructuras de Datos; y, Matemáticas Discretas I o Teoría de Grafos; y, Matemáticas Discretas II o Ecuaciones en Diferencias y Sistemas Dinámicos", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2025963, "Matemáticas Discretas I", 4, "Ciencias de la Computación", "Álgebra Lineal o Álgebra Lineal Básica", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2015184, "Teoría de Grafos", 4, "Ciencias de la Computación", "Álgebra Lineal o Álgebra Lineal Básica; e, Introducción al análisis Combinatorio", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2025964, "Matemáticas Discretas II", 4, "Ciencias de la Computación", "Matemáticas Discretas I o Teoría de Grafos", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2026555, "Álgebra Abstracta Computacional", 4, "Ciencias de la Computación", "Ecuaciones en Diferencias Finitas y Sistemas Dinámicos", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2015970, "Métodos Numéricos", 3, "Ciencias de la Computación", "Cálculo en Varias Variables o Cálculo Vectorial", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2019072, "Análisis Numérico I", 4, "Ciencias de la Computación", "Introducción al Análisis Real", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2015703, "Ingeniería Económica", 3, "Ciencias Económicas y Administrativas", "Cálculo Integral o Cálculo Integral en una variable", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2025986, "Ingeniería Económica y Análisis de Riesgo", 3, "Ciencias Económicas y Administrativas", "Cálculo Integral o Cálculo Integral en una variable; y, Probabilidad y Estadística Fundamental; Probabilidad y Fundamental o Probabilidad; y, Sistemas de Costos", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2016047, "Modelos Económicos Computacionales", 3, "Ciencias Económicas y Administrativas", "Cálculo Integral o Cálculo Integral en una variable", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2015702, "Gerencia y Gestión de Proyectos", 3, "Ciencias Económicas y Administrativas", "Ingeniería Económica o Ingeniería Económica y Análisis de Riesgo o Modelos Económicos Computacionales", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2016028, "Diseño, Gestión y Evaluación de Proyectos", 4, "Ciencias Económicas y Administrativas", "Ingeniería Económica o Ingeniería Económica y Análisis de Riesgo o Modelos Económicos Computacionales", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2016375, "Programación Orientada a Objetos", 3, "Métodos y Tecnologías de Software", "Programación de Computadores o Introducción a las ciencias de la computación.", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2016699, "Estructuras de Datos", 3, "Métodos y Tecnologías de Software", "Programación Orientada a Objetos", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2016701, "Ingeniería de Software I", 3, "Métodos y Tecnologías de Software", "Pensamiento Sistémico; y, Estructuras de Datos; y, Bases de Datos o Análisis de Bases de Datos;", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2016702, "Ingeniería de Software II", 3, "Métodos y Tecnologías de Software", "Ingeniería de Software I; y, Redes de Computadores", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2025966, "Lenguajes de Programación", 3, "Métodos y Tecnologías de Software", "Estructuras de Datos; e, Introducción a la Teoría de la Computación", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2016716, "Arquitectura de Software", 3, "Métodos y Tecnologías de Software", "Ingeniería de Software II", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2015734, "Programación de Computadores", 3, "Métodos y Tecnologías de Software", "-", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2026573, "Introducción a las ciencias de la computación y a la programación", 3, "Métodos y Tecnologías de Software", "-", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2016697, "Arquitectura de Computadores", 3, "Infraestructura Computacional, de Comunicaciones y de Información", "Elementos de Computadores o Electrónica Digital I", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2016707, "Sistemas Operativos", 3, "Infraestructura Computacional, de Comunicaciones y de Información", "Arquitectura de Computadores", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2025967, "Redes de Computadores", 3, "Infraestructura Computacional, de Comunicaciones y de Información", "Fundamentos de Electricidad y Magnetismo; y, Estructuras de Datos; y, Arquitectura de Computadores", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2016722, "Computación Paralela y Distribuida", 3, "Infraestructura Computacional, de Comunicaciones y de Información", "Algoritmos", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2016353, "Bases de Datos", 3, "Infraestructura Computacional, de Comunicaciones y de Información", "Programación Orientada a Objetos", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2025983, "Arquitectura de Infraestructura y gobierno de TICs", 3, "Infraestructura Computacional, de Comunicaciones y de Información", "Ingeniería de Software II y Sistemas de Información", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2016698, "Elementos de Computadores", 3, "Infraestructura Computacional, de Comunicaciones y de Información", "Introducción a la Ingeniería de Sistemas y Computación", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2016498, "Electrónica Digital I", 4, "Infraestructura Computacional, de Comunicaciones y de Información", "Electrónica Análoga I", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2025994, "Teoría de la Información y Sistemas de Comunicaciones", 3, "Infraestructura Computacional, de Comunicaciones y de Información", "Redes de Computadores; y Probabilidad y Estadística Fundamental o Probabilidad", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2016492, "Comunicaciones", 3, "Infraestructura Computacional, de Comunicaciones y de Información", "Líneas y Antenas", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2025982, "Sistemas de Información", 3, "Infraestructura Computacional, de Comunicaciones y de Información", "Bases de Datos o Análisis de Bases de Datos; y, Gerencia y Gestión de Proyectos o Diseño, Gestión y Evaluación de Proyectos; y, Pensamiento Sistémico", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2016053, "Sistemas de Información Gerencial", 4, "Infraestructura Computacional, de Comunicaciones y de Información", "Bases de Datos o Análisis de Bases de Datos; y, Gerencia y Gestión de Proyectos o Diseño, Gestión y Evaluación de Proyectos; y, Pensamiento Sistémico", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2025972, "Introducción a la Criptografía y a la Seguridad de la Información", 3, "Infraestructura Computacional, de Comunicaciones y de Información", "Algoritmos", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2026441, "Información, Codificación y Criptografía.", 4, "Infraestructura Computacional, de Comunicaciones y de Información", "Algoritmos", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2027313, "Teoría de la codificación", 4, "Infraestructura Computacional, de Comunicaciones y de Información", "Algoritmos", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2027310, "Criptografía", 3, "Infraestructura Computacional, de Comunicaciones y de Información", "Introducción a la Criptografía y a la Seguridad de la Información o Información, Codificación y Criptografía.o Teoría de la codificación", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2025960, "Computación Visual", 3, "Computación Visual", "Algoritmos", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2025995, "Introducción a los Sistemas Inteligentes", 3, "Sistemas Inteligentes", "Algoritmos", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2016703, "Pensamiento Sistémico", 3, "Modelos, Sistemas, Optimización y Simulación", "", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2025969, "Modelos Estocásticos y Simulación en Computación y Comunicaciones", 3, "Modelos, Sistemas, Optimización y Simulación", "Optimización o Introducción a la Optimización", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2025970, "Modelos y Simulación", 3, "Modelos, Sistemas, Optimización y Simulación", "Cálculo en Varias Variables o Cálculo Vectorial; y, Matemáticas Discretas II o Ecuaciones en Diferencias Finitas y Sistemas Dinámicos; y, Probabilidad y Estadística Fundamental o  Probabilidad; y , Programación Orientada a Objetos", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2019082, "Modelos matemáticos I", 4, "Modelos, Sistemas, Optimización y Simulación", "Cálculo en Varias Variables o Cálculo Vectorial ; y, Matemáticas Discretas II o Ecuaciones en Diferencias Finitas y Sistemas Dinámicos ; y, Probabilidad y Estadística Fundamental o Probabilidad; y , Programación Orientada a Objetos", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2025971, "Optimización", 3, "Modelos, Sistemas, Optimización y Simulación", "Modelos y Simulación o Modelos Matemáticos", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2015173, "Introducción a la Optimización", 4, "Modelos, Sistemas, Optimización y Simulación", "Modelos y Simulación o Modelos Matemáticos", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2025975, "Introducción a la Ingeniería de Sistemas y Computación", 3, "Contexto Profesional y Proyectos de Ingeniería", "-", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2024045, "Taller de Proyectos Interdisciplinarios", 3, "Contexto Profesional y Proyectos de Ingeniería", "Haber aprobado 40 créditos del Componente de Formación Disciplinar o Profesional; y, Gerencia y Gestión de Proyectos o Diseño, Gestión y Evaluación de Proyectos", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2025974, "Trabajo de Grado - Trabajo Investigativo", 6, "TRABAJO DE GRADO", "Haber aprobado 60 créditos del Componente de Formación Disciplinar o Profesional", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2025973, "Trabajo de Grado - Práctica de Extensión", 6, "TRABAJO DE GRADO", "Haber aprobado 60 créditos del Componente de Formación Disciplinar o Profesional", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2016843, "Trabajo de Grado - Asignaturas de Posgrado", 6, "TRABAJO DE GRADO", "Haber aprobado 60 créditos del Componente de Formación Disciplinar o Profesional", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2016762, "Práctica estudiantil I", 3, "Libre elección", "Haber aprobado 40 créditos del Componente de Formación Disciplinar o Profesional", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2016763, "Práctica estudiantil II", 6, "Libre elección", "Haber aprobado 40 créditos del Componente de Formación Disciplinar o Profesional", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2016764, "Práctica estudiantil III", 9, "Libre elección", "Haber aprobado 40 créditos del Componente de Formación Disciplinar o Profesional", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(1000070, "Práctica Colombia I", 3, "Libre elección", "Haber aprobado 40 créditos del Componente de Formación Disciplinar o Profesional", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(1000071, "Práctica Colombia II", 6, "Libre elección", "Haber aprobado 40 créditos del Componente de Formación Disciplinar o Profesional", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(1000072, "Práctica Colombia III", 9, "Libre elección", "Haber aprobado 40 créditos del Componente de Formación Disciplinar o Profesional", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2015168, "Fundamentos de Matemáticas", 4, "Libre elección", "-", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2015181, "Sistemas Numéricos", 4, "Libre elección", "Fundamentos de Matemáticas", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2027641, "Análisis de bases de datos", 3, "Libre elección", "Programación Orientada a Objetos", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2015155, "Introducción al Análisis Real", 4, "Libre elección", "Cálculo en Varias Variables o Cálculo Vectorial; y, Sistemas Numéricos", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2025819, "Introducción a la Teoría de Conjuntos", 4, "Libre elección", "Fundamentos de Matemáticas", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2026548, "Introducción al Análisis Combinatorio", 4, "Libre elección", "Introducción a la Teoría de Conjuntos", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2026519, "Ecuaciones en Diferencias Finitas y Sistemas Dinámicos", 4, "Libre elección", "Álgebra Lineal o Álgebra Lineal Básica; y, Sistemas Numéricos", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2016366, "Estadística Descriptiva y Exploratoria", 4, "Libre elección", "-", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2016379, "Inferencia Estadística", 4, "Libre elección", "Probabilidad y Estadística Fundamental o Probabilidad", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2016360, "Análisis de Regresión", 4, "Libre elección", "Inferencia Estadística", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(1000007, "Ecuaciones Diferenciales", 4, "Libre elección", "Cálculo Integral o Cálculo integral en una variable; y Álgebra Lineal o Álgebra Lineal Básica", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2016342, "Cálculo de ecuaciones diferenciales ordinarias", 4, "Libre elección", "Cálculo Integral o Cálculo integral en una variable; y Álgebra Lineal o Álgebra Lineal Básica", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2016495, "Electrónica Análoga I", 4, "Libre elección", "Circuitos Eléctricos I", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2016489, "Circuitos Eléctricos I", 3, "Libre elección", "Taller de Ingeniería Electrónica; y, Cálculo Diferencial o Cálculo Diferencial en una Variable", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2016509, "Taller de Ingeniería Electrónica", 2, "Libre elección", "Introducción a Ingeniería de Sistemas y Computación", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2016503, "Líneas y Antenas", 3, "Libre elección", "Campos Electromagnéticos", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2016487, "Campos Electromagnéticos", 4, "Libre elección", "Cálculo en Varias Variables o Cálculo Vectorial; y, Fundamentos de Electricidad y Magnetismo; y, Señales y Sistemas I", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2016506, "Señales y Sistemas I", 3, "Libre elección", "Circuitos Eléctricos I; y, Ecuaciones Diferenciales o Cálculo de Ecuaciones Diferenciales Ordinarias", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2015159, "Variable Compleja", 4, "Libre elección", "Ecuaciones Diferenciales o Cálculo de Ecuaciones Diferenciales Ordinarias", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2016507, "Señales y Sistemas II", 3, "Libre elección", "Señales y Sistemas I y Variable compleja", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2016499, "Electrónica Digital II", 4, "Libre elección", "Electrónica Digital I", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2016508, "Sistemas Embebidos", 3, "Libre elección", "Electrónica Digital II", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2016512, "Verificación de Sistemas Digitales", 3, "Libre elección", "Electrónica Digital II", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2017271, "Principios de Dinámica", 3, "Libre elección", "Álgebra Lineal o Álgebra Lineal Básica", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2017287, "Sensores y Actuadores", 3, "Libre elección", "Señales y Sistemas II", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2016493, "Control", 4, "Libre elección", "Señales y Sistemas II", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2016770, "Robótica", 3, "Libre elección", "Principios de Dinámica; y Control", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2016610, "Sistemas de Costos", 4, "Libre elección", "Economía General", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2016592, "Economía General", 3, "Libre elección", "-", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(1000009, "Biología General", 3, "Libre elección", "-", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(1000010, "Biología Molecular y Celular", 3, "Libre elección", "-", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2016099, "Taller Forma y Estructura", 5, "Libre elección", "-", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2016069, "Fundamentos Tecnológicos: Color y Producción", 3, "Libre elección", "Taller Forma y Estructura", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2016061, "Bocetación e Ilustración", 3, "Libre elección", "Fundamentos Tecnológicos: Color y Producción", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2016083, "Producción en Medios Digitales", 3, "Libre elección", "Bocetación e Ilustración", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2016091, "Taller de Énfasis en Multimedia e Imagen Digital 1", 3, "Libre elección", "Producción en Medios Digitales", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2016093, "Taller de Énfasis en animación y narrativas audiovisuales I", 3, "Libre elección", "Producción en Medios Digitales", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2025987, "Modelos estocásticos para procesos de manufactura y sistemas de servicios", 3, "Libre elección", "Optimización o Introducción a la Optimización; y, Modelos y Simulación o Modelos Matemáticos", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(2025988, "Taller de simulación procesos de manufactura y sistemas de servicios", 3, "Libre elección", "Modelos estocásticos para procesos de manufactura y sistemas de servicios", "COMPONENTE DE FUNDAMENTACIÓN") );
        Subjects.add( new Subject(1000089, "Cátedra nacional de inducción y preparación para la vida", 2, "Libre elección", "", "COMPONENTE DE LIBRE ELECCIÓN") );

        loadSubjects(Subjects);

    }
}
