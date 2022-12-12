package com.krzysztof.computerprice.service;

import com.krzysztof.computerprice.currency.Rate;
import com.krzysztof.computerprice.entity.Computer;
import com.krzysztof.computerprice.repository.ComputerRepository;
import lombok.SneakyThrows;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ComputerService {

    private static final String NBP_CURRENCY_RATE_URL = "http://api.nbp.pl/api/exchangerates/rates/A/USD/";
    private static final String JSON_FORMAT = "?format=json";
    private static RestTemplate restTemplate = new RestTemplate();

    private ComputerRepository repository;

    public ComputerService(ComputerRepository repository) {
        this.repository = repository;
    }

    public Computer createComputer(Computer computer) {
        if (computer.getPostingDate() == null) {
            computer.setPostingDate(LocalDate.now());
        }

        double usdRate = checkRate(computer.getPostingDate()).getValue();

        if (usdRate == 0) {
            System.out.println("Brak danych o kursie na dany dzień");
        }

        computer.setPrice_PLN(round((computer.getPrice_USD() * usdRate), 2));

        return repository.save(computer);
    }

    public List<Computer> findAllByDate(String dateString) {

        List<Computer> result = new ArrayList<>();

        try {
            LocalDate given = LocalDate.parse(dateString);
            result = repository.findAllByPostingDate(given);
        } catch (DateTimeParseException e) {
            System.out.println(e);
        }
        return result;
    }

    public List<Computer> sortListByGivenProperty(String dir, String property) {
        List<Computer> computers = new ArrayList<>();

        try {
            computers = repository.findAll(Sort.by(Sort.Direction.valueOf(dir), property));
            return computers;
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            System.out.println("Niepoprawnie podane właściwości sortowania");
            return computers;
        }
    }

    public List<Computer> findAllByNamePart(String part) {
        return repository.findAllByNameIgnoreCaseContains(part);
    }

    public Optional<Computer> findByName(String name) {
        return repository.findByNameIgnoreCase(name);
    }

    public Optional<Computer> findById(int id) {
        return repository.findById(id);
    }

    public List<Computer> getComputers() {
        return repository.findAll();
    }

    @SneakyThrows
    private static Rate checkRate(LocalDate date) {
        Rate rate = new Rate(0d);

        try {
            String response = restTemplate.getForObject( NBP_CURRENCY_RATE_URL + date.toString() + JSON_FORMAT,
                    String.class);

            JSONObject jsonObject = new JSONObject(response);

            JSONArray jsonArray = jsonObject.getJSONArray("rates");

            JSONObject inner = jsonArray.getJSONObject(0);

            String value = inner.getString("mid");

            rate.setValue(Double.parseDouble(value));
        } catch (HttpClientErrorException e) {
            System.out.println(e);
        }

        return rate;
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /*
    wprowadzić obsługę błędów
     */
}
