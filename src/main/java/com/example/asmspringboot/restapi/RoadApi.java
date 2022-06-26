package com.example.asmspringboot.restapi;

import com.example.asmspringboot.entity.Road;
import com.example.asmspringboot.service.RoadService;
import com.example.asmspringboot.specification.SearchBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roads")
@CrossOrigin("*")
public class RoadApi {
    @Autowired
    RoadService roadService;

    @RequestMapping(method = RequestMethod.GET)
    public  ResponseEntity<?> getAllRoad(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "status", defaultValue = "1") Integer status,
            @RequestParam(name = "district_id",defaultValue = "-1") Integer districtId){
        SearchBody searchBody = SearchBody.builder()
                .roadName(name)
                .districtId(districtId)
                .status(status)
                .build();
        return ResponseEntity.ok(roadService.findAllAndFilter(searchBody));
    }
//    @RequestMapping(method = RequestMethod.POST)
//    public ResponseEntity<Map<String, Object>> getAllRoad(@RequestParam(value = "search") String search, @RequestBody SearchBody searchBody) {
//        try {
//            List<Road> roads;
//            roads = roadService.findAllAndFilter(searchBody).getBody();
//            Map<String, Object> response = new HashMap<>();
//            response.put("roads", roads);
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

//
//    @RequestMapping(method = RequestMethod.GET)
//    @ResponseBody
//    public List<Road> search(@RequestParam(value = "search") String search) {
//        UserSpecificationsBuilder builder = new UserSpecificationsBuilder();
//        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
//        Matcher matcher = pattern.matcher(search + ",");
//        while (matcher.find()) {
//            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
//        }
//
//        Specification<Road> spec = builder.build();
//        return roadService.findAllAndFilter(spec);
//    }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Road> create(@RequestBody Road road) {
        return ResponseEntity.ok(roadService.save(road));
    }
}
