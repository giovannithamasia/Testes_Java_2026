package entrega_atividades.Aula5.reforco;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConversorTemperaturaTest {

    @DisplayName("Conversão de Celsius para Fahrenheit")
    @ParameterizedTest(name = "{0} ºC = {1} ºF")
    @CsvSource({
            "0,32",
            "10,50",
            "20,68",
            "30,86",
            "100,212",
            "-10,14",
            "-40,-40"
    })
    void converterTemperaturaCelsiusParaFahrenheit(double celsius,double esperado){
        double resultado = ConversorTemperatura.celsiusParaFahrenheit(celsius);

        assertEquals(esperado,resultado,0.001);
    }

}
