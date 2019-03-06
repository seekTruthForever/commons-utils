package com.whv.commons.utils.date;

import java.util.Calendar;
import java.util.Date;
/**
 * 农历类
 * @author huawei
 *
 */
public class Lunar
{

    public Lunar()
    {
        i = 0;
    }

    public String cDay(int d)
    {
        String s;
        switch(d)
        {
        case 1: // '\001'
            s = (new StringBuilder(String.valueOf(monthNong[getMonth()]))).append("\u6708").toString();
            break;

        case 10: // '\n'
            s = "\u521D\u5341";
            break;

        case 20: // '\024'
            s = "\u4E8C\u5341";
            break;

        case 30: // '\036'
            s = "\u4E09\u5341";
            break;

        default:
            s = nStr2[d / 10];
            s = (new StringBuilder(String.valueOf(s))).append(nStr1[d % 10]).toString();
            break;
        }
        return s;
    }

    public String cyclical(int num)
    {
        return (new StringBuilder(String.valueOf(Gan[num % 10]))).append(Zhi[num % 12]).toString();
    }

    public String cYear(int y)
    {
        String s;
        int d;
        for(s = " "; y > 0; s = (new StringBuilder(String.valueOf(yearName[d]))).append(s).toString())
        {
            d = y % 10;
            y = (y - d) / 10;
        }

        return s;
    }

    public int getDay()
    {
        return day;
    }

    public int getDayCyl()
    {
        return dayCyl;
    }

    public boolean getIsLeap()
    {
        return isLeap;
    }

    public String getLunar(int year, int month, int day)
    {
        int SY = year;
        int SM = month;
        int SD = day;
        int _tmp = (SY - 4) % 12;
        Calendar cl = Calendar.getInstance();
        cl.set(SY, SM - 1, SD);
        Date sDObj = cl.getTime();
        Lunar1(sDObj);
        String s = cDay(getDay());
        return s;
    }

    public int getMonCyl()
    {
        return monCyl;
    }

    public int getMonth()
    {
        return month;
    }

    public int getYear()
    {
        return year;
    }

    public int getYearCyl()
    {
        return yearCyl;
    }

    public int leapDays(int y)
    {
        if(leapMonth(y) != 0)
            return (lunarInfo[y - 1900] & 65536) != 0 ? 30 : 29;
        else
            return 0;
    }

    public int leapMonth(int y)
    {
        if(y < 1900)
            throw new RuntimeException((new StringBuilder("\u5DE5\u4F5C\u65E5\u5386\u5E74\u4EFD")).append(y).append("\u4E0D\u80FD\u4F4E\u4E8E1901\u5E74\u3002").toString());
        if(y > 2049)
            throw new RuntimeException((new StringBuilder("\u5DE5\u4F5C\u65E5\u5386\u5E74\u4EFD")).append(y).append("\u4E0D\u80FD\u9AD8\u4E8E2049\u5E74\u3002").toString());
        else
            return lunarInfo[y - 1900] & 15;
    }

    public void Lunar1(Date objDate)
    {
        int leap = 0;
        int temp = 0;
        Calendar cl = Calendar.getInstance();
        cl.set(1900, 0, 31);
        Date baseDate = cl.getTime();
        int offset = (int)((objDate.getTime() - baseDate.getTime()) / 86400000L);
        dayCyl = offset + 40;
        monCyl = 14;
        int i;
        for(i = 1900; i < 2050 && offset > 0; i++)
        {
            temp = lYearDays(i);
            offset -= temp;
            monCyl += 12;
        }

        if(offset < 0)
        {
            offset += temp;
            i--;
            monCyl -= 12;
        }
        year = i;
        yearCyl = i - 1864;
        leap = leapMonth(i);
        isLeap = false;
        for(i = 1; i < 13 && offset > 0; i++)
        {
            if(leap > 0 && i == leap + 1 && !isLeap)
            {
                i--;
                isLeap = true;
                temp = leapDays(year);
            } else
            {
                temp = monthDays(year, i);
            }
            if(isLeap && i == leap + 1)
                isLeap = false;
            offset -= temp;
            if(!isLeap)
                monCyl++;
        }

        if(offset == 0 && leap > 0 && i == leap + 1)
            if(isLeap)
            {
                isLeap = false;
            } else
            {
                isLeap = true;
                i--;
                monCyl--;
            }
        if(offset < 0)
        {
            offset += temp;
            i--;
            monCyl--;
        }
        month = i;
        day = offset + 1;
    }

    public int lYearDays(int y)
    {
        if(y - 1900 < 0 || y - 1900 >= lunarInfo.length)
            return 0;
        int li = lunarInfo[y - 1900];
        int sum = 348;
        for(int i = 32768; i > 8; i >>= 1)
            sum += (li & i) != 0 ? 1 : 0;

        return sum + leapDays(y);
    }

    public int monthDays(int y, int m)
    {
        return (lunarInfo[y - 1900] & 65536 >> m) != 0 ? 30 : 29;
    }

    int monCyl;
    int dayCyl;
    int yearCyl;
    int year;
    int month;
    int day;
    int i;
    boolean isLeap;
    int lunarInfo[] = {
        19416, 19168, 42352, 21717, 53856, 55632, 91476, 22176, 39632, 21970, 
        19168, 42422, 42192, 53840, 119381, 46400, 54944, 44450, 38320, 84343, 
        18800, 42160, 46261, 27216, 27968, 109396, 11104, 38256, 21234, 18800, 
        25958, 54432, 59984, 28309, 23248, 11104, 100067, 37600, 116951, 51536, 
        54432, 120998, 46416, 22176, 107956, 9680, 37584, 53938, 43344, 46423, 
        27808, 46416, 86869, 19872, 42448, 83315, 21200, 43432, 59728, 27296, 
        44710, 43856, 19296, 43748, 42352, 21088, 62051, 55632, 23383, 22176, 
        38608, 19925, 19152, 42192, 54484, 53840, 54616, 46400, 46496, 103846, 
        38320, 18864, 43380, 42160, 45690, 27216, 27968, 44870, 43872, 38256, 
        19189, 18800, 25776, 29859, 59984, 27480, 21952, 43872, 38613, 37600, 
        51552, 55636, 54432, 55888, 30034, 22176, 43959, 9680, 37584, 51893, 
        43344, 46240, 47780, 44368, 21977, 19360, 42416, 86390, 21168, 43312, 
        31060, 27296, 44368, 23378, 19296, 42726, 42208, 53856, 60005, 54576, 
        23200, 30371, 38608, 19415, 19152, 42192, 118966, 53840, 54560, 56645, 
        46496, 22224, 21938, 18864, 42359, 42160, 43600, 111189, 27936, 44448
    };
    int solarMonth[] = {
        31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 
        30, 31
    };
    String Gan[] = {
        "\u7532", "\u4E59", "\u4E19", "\u4E01", "\u620A", "\u5DF1", "\u5E9A", "\u8F9B", "\u58EC", "\u7678"
    };
    String Zhi[] = {
        "\u5B50", "\u4E11", "\u5BC5", "\u536F", "\u8FB0", "\u5DF3", "\u5348", "\u672A", "\u7533", "\u9149", 
        "\u620C", "\u4EA5"
    };
    String Animals[] = {
        "\u9F20", "\u725B", "\u864E", "\u5154", "\u9F99", "\u86C7", "\u9A6C", "\u7F8A", "\u7334", "\u9E21", 
        "\u72D7", "\u732A"
    };
    int sTermInfo[] = {
        0, 21208, 42467, 63836, 85337, 107014, 128867, 150921, 173149, 195551, 
        218072, 240693, 263343, 285989, 308563, 331033, 353350, 375494, 397447, 419210, 
        440795, 462224, 483532, 504758
    };
    String nStr1[] = {
        "\u65E5", "\u4E00", "\u4E8C", "\u4E09", "\u56DB", "\u4E94", "\u516D", "\u4E03", "\u516B", "\u4E5D", 
        "\u5341"
    };
    String nStr2[] = {
        "\u521D", "\u5341", "\u5EFF", "\u5345", "\u3000"
    };
    String monthNong[] = {
        "\u6B63", "\u6B63", "\u4E8C", "\u4E09", "\u56DB", "\u4E94", "\u516D", "\u4E03", "\u516B", "\u4E5D", 
        "\u5341", "\u5341\u4E00", "\u5341\u4E8C"
    };
    String yearName[] = {
        "\u96F6", "\u58F9", "\u8D30", "\u53C1", "\u8086", "\u4F0D", "\u9646", "\u67D2", "\u634C", "\u7396"
    };

}

