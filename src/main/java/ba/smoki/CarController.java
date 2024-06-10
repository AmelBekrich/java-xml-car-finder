package ba.smoki;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public String displayCars() {
        return "cars";
    }

    @GetMapping("/brand")
    public String getCarsByBrand(@RequestParam String brand, Model model) {
        List<Car> carList = carService.getCarByBrand(brand);
        model.addAttribute("cars", carList);
        return  "car-list";
    }

    @GetMapping("/year")
    public String getCarsByYear(@RequestParam int startingYear,@RequestParam int endingYear, Model model) {
        List<Car> carList = carService.getCarByYear(startingYear, endingYear);
        model.addAttribute("cars", carList);
        return "car-list";
    }

    @GetMapping("/consumption")
    public String getCarsByConsumption(@RequestParam(required = false) double lowestConsumption,@RequestParam(required = false) double highestConsumption, Model model) {
        List<Car> carList = carService.getCarByConsumption(lowestConsumption, highestConsumption);
        model.addAttribute("cars", carList);
        return "car-list";
    }
}
