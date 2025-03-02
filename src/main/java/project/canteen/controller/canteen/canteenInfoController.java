package project.canteen.controller.canteen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import project.canteen.common.constant;
import project.canteen.entity.canteen.canteenInfo;
import project.canteen.model.auth.ResponMessage;
import project.canteen.service.canteen.CanteenInfoService;

@RestController
@RequestMapping(constant.API.PREFIX)
public class canteenInfoController {
    @Autowired
    private CanteenInfoService canteenInfoService;

    @PostMapping (value = "/canteenInfo/update")
    @PreAuthorize("hasRole('ROLE_STAFF')")
    @ResponseBody
    public ResponMessage updateCanteenInfo(@RequestBody canteenInfo canteenInfo) {
        return canteenInfoService.update(canteenInfo);
    }

    @GetMapping(value = "/canteenInfo/get")
    @PreAuthorize("hasRole('ROLE_STAFF')")
    @ResponseBody
    public ResponMessage getCanteenInfo() {
        return canteenInfoService.getCanteenInfo();
    }
}
