package ba.smoki;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    private List<Car> cars;

    @PostConstruct
    public void init() {
        try {
            File carFile = new File("/Users/amelbekrich/IdeaProjects/CarsAssignment/src/main/resources/cars.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Cars.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Cars carWrapper = (Cars) unmarshaller.unmarshal(carFile);
            this.cars = carWrapper.getCars();
        } catch (JAXBException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Car> getCarByBrand(String brand) {
        return cars.stream()
                .filter(car -> car.getBrand()
                .equalsIgnoreCase(brand))
                .collect(Collectors.toList());
    }

    public List<Car> getCarByYear(int startingYear, int endingYear) {
        return cars.stream()
                .filter(car -> car.getYear() >= startingYear && car.getYear() <= endingYear)
                .collect(Collectors.toList());
    }

    public List<Car> getCarByConsumption(Double lowestConsumption, Double highestConsumption) {
        return cars.stream()
                .filter(car -> (lowestConsumption == null || car.getConsumption() >= lowestConsumption) &&
                        (highestConsumption == null || car.getConsumption() <= highestConsumption))
                .collect(Collectors.toList());
    }


}
