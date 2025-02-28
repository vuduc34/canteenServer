package project.canteen.controller.canteen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.canteen.common.constant;
import project.canteen.model.auth.ResponMessage;
import project.canteen.service.canteen.categoryService;
import project.canteen.service.canteen.foodService;

@RestController
@RequestMapping(constant.API.PREFIX_AUTH)
public class foodController {
    @Autowired
    private foodService foodService;

    @GetMapping("/food/findAll")
    @ResponseBody
    public ResponMessage findAll() {
        return foodService.findAlll();
    }

    @GetMapping("/food/findByCategoryId")
    @ResponseBody
    public ResponMessage findByCategoryId(@RequestParam Long categoryId) {
        return foodService.findByCategory(categoryId);
    }
}
