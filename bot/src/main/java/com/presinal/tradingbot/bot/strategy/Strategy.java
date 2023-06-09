/*
 * The MIT License
 *
 * Copyright 2018 Miguel Presinal.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.presinal.tradingbot.bot.strategy;

import com.presinal.tradingbot.bot.action.BotActionContext;
import java.time.Instant;
import java.util.Calendar;
import com.presinal.tradingbot.market.client.MarketClient;
import com.presinal.tradingbot.market.client.types.AssetPair;
import com.presinal.tradingbot.bot.strategy.listener.StrategyListener;
import com.presinal.tradingbot.indicator.datareader.PeriodIndicatorDataReader;

/**
 *
 * @author Miguel Presinal<presinal378@gmail.com>
 * @since 1.0
 */
public interface Strategy extends Runnable{
    
    void setListener(StrategyListener listener);
    
    Strategy getImpl();
    
    void setClient(MarketClient client);
    
    void setAsset(AssetPair asset);
    
    void setContext(BotActionContext context);
    
    public default void computeDataReaderDateRange(PeriodIndicatorDataReader dataReader) {

        long perioTimestamp = dataReader.getTimeFrame().toMilliSecond() * dataReader.getPeriod();

        Calendar cal = Calendar.getInstance();
        Instant endDate = Instant.now();

        // remove second to avoid invalid date range
        cal.setTimeInMillis(endDate.toEpochMilli());
        endDate = Instant.ofEpochMilli(endDate.toEpochMilli() - (cal.get(Calendar.SECOND) * 1000));
        Instant startDate = Instant.ofEpochMilli(endDate.toEpochMilli() - perioTimestamp);
        dataReader.setDateRange(startDate, endDate);
    }
    
    public default void notifySignal(BuySellSignal signal, StrategyListener listener) {
        if (listener != null) {
            listener.onSignal(signal, getImpl());
        }
    }
}
