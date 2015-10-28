package ru.timurnav.reader;

//import com.bc.capman.exreconcile.common.ReconcileLoader;
//import com.bc.capman.exreconcile.common.ReconcileResult;
//import com.bc.capman.exreconcile.common.TradeInfo;
//import com.bc.capman.exreconcile.common.TradeRecord;
//import com.bc.capman.exreconcile.common.xml.XmlStreamUtil;
//import com.bc.capman.web.common.shared.state.StateType;
//import com.bc.capman.web.common.shared.util.TimeUtil;
//import org.apache.log4j.Logger;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * User: gkislin
 * Date: 10/31/11
 */
public class AlorLoader /*implements ReconcileLoader */{
/*

    public static final String NAME = "ALOR";

    private static final String POSITION_HEADER = "Открытые позиции срочный рынок";
    private static final String TRADE_HEADER = "Совершенные сделки РТС (FORTS)";
    private static final String TRADE_HEADER2 = "Совершенные сделки Срочный рынок FORTS";

    private static final String PERIOD_HEADER = "Активы по счетам";
    private static final String ROW = "row";
    private static final String CELL = "cell";
    private static final String CELL_VALUE = "cellValue";
    private static final String ROWS_COUNT = "rowsCount";

    private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private SimpleDateFormat reportDateFormat = new SimpleDateFormat("dd.MM.yy");
    private long startTime;
    private static final Logger LOGGER = Logger.getLogger(AlorLoader.class);

    public AlorLoader() {
        dateFormat.setLenient(false);
        reportDateFormat.setLenient(false);
        try {
            startTime = TimeUtil.separateTime(dateFormat.parse("19:00:00"));
        } catch (ParseException e) {
            throw new IllegalStateException("AlorProcessor initialization error");
        }
    }

    @Override
    public ReconcileResult check(InputStream is) {
        XMLStreamReader reader = null;
        ReconcileResult result = new ReconcileResult();
        LOGGER.info("Start parsing...\n");
        try {
            int count = 0;

            reader = XmlStreamUtil.createReader(is, "windows-1251");
            XmlStreamUtil.doUntil(reader, XMLEvent.CHARACTERS, PERIOD_HEADER);
            XmlStreamUtil.doUntil(reader, XMLEvent.START_ELEMENT, ROW);
            XmlStreamUtil.doUntil(reader, XMLEvent.END_ELEMENT, CELL);

            long startDate = reportDateFormat.parse(getCellValue(reader)).getTime();
            long endDate = reportDateFormat.parse(getCellValue(reader)).getTime();

            String header = XmlStreamUtil.doUntilAny(reader, XMLEvent.CHARACTERS, POSITION_HEADER, TRADE_HEADER, TRADE_HEADER2);
            if (POSITION_HEADER.equals(header)) {
                while (true) {
                    XmlStreamUtil.doUntil(reader, XMLEvent.END_ELEMENT, ROW);
                    if (!ROW.equals(XmlStreamUtil.getNextValue(XMLEvent.START_ELEMENT, reader))) {
                        break;
                    }
                    XmlStreamUtil.doUntil(reader, XMLEvent.END_ELEMENT, CELL, 3);
                    String contract = getCellValue(reader);
                    int start = Integer.valueOf(getCellValue(reader));
                    int end = Integer.valueOf(getCellValue(reader));
                    result.addTradeInfo(contract, start, end);
                }
                XmlStreamUtil.doUntil(reader, XMLEvent.CHARACTERS, TRADE_HEADER);
            }

            int tradeNumber = Integer.valueOf(XmlStreamUtil.getElementValue(reader, ROWS_COUNT)) - 1;
            result.init(tradeNumber, startDate, endDate, startTime);

            // parse data
            while (true) {
                XmlStreamUtil.doUntil(reader, XMLEvent.END_ELEMENT, ROW);
                if (!ROW.equals(XmlStreamUtil.getNextValue(XMLEvent.START_ELEMENT, reader))) {
                    break;
                }
                XmlStreamUtil.doUntil(reader, XMLEvent.END_ELEMENT, CELL, 2);
                String exchangeTradeId = getCellValue(reader);
                String accountId = getCellValue(reader).substring(4);
                result.addAccount(accountId);

                XmlStreamUtil.doUntil(reader, XMLEvent.END_ELEMENT, CELL, 2);

                long time = TimeUtil.separateTime(dateFormat.parse(getCellValue(reader)));
                time += (time > startTime) ? startDate : endDate;

                String contract = getCellValue(reader);
                XmlStreamUtil.doUntil(reader, XMLEvent.END_ELEMENT, CELL, 2);
                int lots = Integer.valueOf(getCellValue(reader));
                double price = Double.valueOf(getCellValue(reader));
                double variationMargin = Double.valueOf(getCellValue(reader));
                double commission = Double.valueOf(getCellValue(reader));

                TradeInfo tradeInfo = result.getTradeInfo(contract);
                TradeRecord record = new TradeRecord(time, accountId, exchangeTradeId, contract, lots, price, commission, tradeInfo.current, variationMargin / lots);
                result.addRecord(record, tradeInfo);
                count++;
            }
            result.initCheckStatus(count);

        } catch (Exception e) {
            if (reader == null) {
                result.setState(StateType.ERROR, "XMLStreamReader initialization error", e);
            } else {
                result.setState(StateType.ERROR, "Parse error in " + reader.getLocation().getLineNumber() + ":" + reader.getLocation().getColumnNumber(), e);
            }
        } finally {
            XmlStreamUtil.close(reader);
        }
        return result;
    }

    @Override
    public String getName() {
        return NAME;
    }

    private static String getCellValue(XMLStreamReader reader) throws XMLStreamException {
        return XmlStreamUtil.getElementValue(reader, CELL_VALUE);
    }
*/
}
