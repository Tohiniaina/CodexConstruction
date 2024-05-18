package codex.evaluation.service;

import codex.evaluation.model.Error;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class ErreurService {

    /*public List<Error> checkError(List<Import> datas) {
        List<Error> errorList = new ArrayList<>();

        for (Import data: datas) {
            if (estUneDate(data.getDate()) == false) {
                errorList.add(new Error(data.getId(), "le format du date semble incorrect"));
            }
        }

        return errorList;
    }

    public static boolean estUneDate(String strDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(strDate.trim());
        } catch (Exception e) {
            return false;
        }
        return true;
    }*/
}
