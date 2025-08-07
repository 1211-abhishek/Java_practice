package org.example;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.JsonGeneratorDelegate;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.modelmapper.ModelMapper;

import java.io.File;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {

        String path = "C:\\Users\\Sreenivas Bandaru\\Documents\\Object_Mapper\\employee app\\employee.txt";
        File employeeFile = new File(path);

        String jsonData = "{\"employeeId\":101,\"name\":{\"first\":\"Abhishek\",\"last\":\"Ghogare\"},\"email\":\"abhishek.ghogare@example.com\",\"phone\":\"+91-9876543210\",\"status\":\"ACTIVE\",\"department\":{\"id\":10,\"name\":\"Engineering\"},\"skills\":[{\"name\":\"Java\",\"experienceYears\":5},{\"name\":\"Spring Boot\",\"experienceYears\":3}],\"projects\":[{\"projectId\":\"PRJ001\",\"name\":\"Hostel App\",\"client\":\"EduHost\",\"startDate\":\"2023-01-15\"}],\"address\":{\"city\":\"Pune\",\"state\":\"Maharashtra\",\"postalCode\":\"411005\"},\"emergencyContact\":{\"name\":\"Rajendra Ghogare\",\"relation\":\"Father\",\"phone\":\"+91-9876543211\"}}";

        System.out.println("String json data : " + jsonData);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonData);

        ObjectNode objectNode = (ObjectNode) jsonNode;

        System.out.println("---------------");
        JsonNode jsonNode1 = objectNode.put("name", "a");
        System.out.println("jj : " + jsonNode1);

//        System.out.println("jsonNode : " + jsonNode);
//        System.out.println("------------------------------------");
//        System.out.println(jsonNode.findValues("name"));
//        System.out.println("------------------- as text  -----------------");
//        System.out.println(jsonNode.findValuesAsText("name"));

//        Iterator<String> it1 = jsonNode.fieldNames();
//        Iterator<JsonNode> it2 = jsonNode.iterator();
//
//        while (it1.hasNext()){
//            System.out.println(it1.next() + "  :  "+it2.next());
//        }

//        Iterator<JsonNode> iterator = jsonNode.iterator();
//
//        while (iterator.hasNext()){
//
//            System.out.println("key : " + " " + "value : " + iterator.next());
//        }


//        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY,true);
//        Employee employee = new Employee(1211,"Abhishek",23, 21000);
//
//        EmployeeDTO employeeDTO = objectMapper.convertValue(employee, EmployeeDTO.class);
//        System.out.println(employeeDTO);

//        objectMapper.writeTree();            // --
//        objectMapper.treeToValue();
//        objectMapper.valueToTree();
//        objectMapper.readValue();
//        objectMapper.writeValue();
//        objectMapper.writeValueAsString();
//        objectMapper.convertValue();

//        ObjectNode objectNode = (ObjectNode) jsonNode;
//        objectNode.
//        JsonGenerator jsonGenerator = new JsonGeneratorDelegate();

//        ModelMapper modelMapper = new ModelMapper();
//        modelMapper.addConverter();
//


    }
}