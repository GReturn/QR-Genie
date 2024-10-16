package io.lin.qrgenie.datahandler;

import com.poiji.bind.Poiji;
import com.poiji.option.PoijiOptions;
import io.lin.qrgenie.Participant;

import java.io.File;
import java.util.List;

public class ExcelService {
    private final PoijiOptions.PoijiOptionsBuilder builder;

    public ExcelService() {
        builder = PoijiOptions.PoijiOptionsBuilder.settings().sheetIndex(0);
    }

    public List<Participant> generateList() {
        PoijiOptions poiji = builder.build();
        return Poiji.fromExcel(new File(FileService.getCsvPath()), Participant.class, poiji);
    }
}
