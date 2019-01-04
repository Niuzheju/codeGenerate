package ${servicePackage};

import com.fx.enums.StateEnum;
import com.fx.model.result.Result;
import ${fullDaoClass};
import ${fullQueryClass};
import ${fullVoClass};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ${serviceClass} {

    @Autowired
    private ${daoClass} ${daoVar};


    public Result getList(${queryClass} ${queryVar}) {
        try {
            int count = ${daoVar}.getCount(${queryVar});
            List<${voClass}> list =  ${daoVar}.getList(${queryVar});
            return Result.rows(list, count);
        } catch (Exception e) {
            LogUtil.error(e);
        }
        return Result.newFailed(StateEnum.ERROR_SYSTEM);
    }
}
