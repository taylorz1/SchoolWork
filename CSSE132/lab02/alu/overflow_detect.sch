<?xml version="1.0" encoding="UTF-8"?>
<drawing version="7">
    <attr value="spartan3e" name="DeviceFamilyName">
        <trait delete="all:0" />
        <trait editname="all:0" />
        <trait edittrait="all:0" />
    </attr>
    <netlist>
        <signal name="op" />
        <signal name="a" />
        <signal name="b" />
        <signal name="r" />
        <signal name="XLXN_5" />
        <signal name="XLXN_6" />
        <signal name="XLXN_7" />
        <signal name="XLXN_8" />
        <signal name="XLXN_9" />
        <signal name="XLXN_10" />
        <signal name="XLXN_11" />
        <signal name="XLXN_12" />
        <signal name="XLXN_13" />
        <signal name="XLXN_14" />
        <signal name="XLXN_15" />
        <signal name="XLXN_16" />
        <signal name="XLXN_17" />
        <signal name="XLXN_18" />
        <signal name="XLXN_19" />
        <signal name="XLXN_20" />
        <signal name="XLXN_21" />
        <signal name="XLXN_22" />
        <signal name="XLXN_23" />
        <signal name="XLXN_24" />
        <signal name="XLXN_25" />
        <signal name="XLXN_26" />
        <signal name="XLXN_27" />
        <signal name="overflow" />
        <port polarity="Input" name="op" />
        <port polarity="Input" name="a" />
        <port polarity="Input" name="b" />
        <port polarity="Input" name="r" />
        <port polarity="Output" name="overflow" />
        <blockdef name="and4">
            <timestamp>2000-1-1T10:10:10</timestamp>
            <line x2="64" y1="-112" y2="-112" x1="144" />
            <arc ex="144" ey="-208" sx="144" sy="-112" r="48" cx="144" cy="-160" />
            <line x2="144" y1="-208" y2="-208" x1="64" />
            <line x2="64" y1="-64" y2="-256" x1="64" />
            <line x2="192" y1="-160" y2="-160" x1="256" />
            <line x2="64" y1="-256" y2="-256" x1="0" />
            <line x2="64" y1="-192" y2="-192" x1="0" />
            <line x2="64" y1="-128" y2="-128" x1="0" />
            <line x2="64" y1="-64" y2="-64" x1="0" />
        </blockdef>
        <blockdef name="inv">
            <timestamp>2000-1-1T10:10:10</timestamp>
            <line x2="64" y1="-32" y2="-32" x1="0" />
            <line x2="160" y1="-32" y2="-32" x1="224" />
            <line x2="128" y1="-64" y2="-32" x1="64" />
            <line x2="64" y1="-32" y2="0" x1="128" />
            <line x2="64" y1="0" y2="-64" x1="64" />
            <circle r="16" cx="144" cy="-32" />
        </blockdef>
        <blockdef name="or4">
            <timestamp>2000-1-1T10:10:10</timestamp>
            <line x2="48" y1="-64" y2="-64" x1="0" />
            <line x2="64" y1="-128" y2="-128" x1="0" />
            <line x2="64" y1="-192" y2="-192" x1="0" />
            <line x2="48" y1="-256" y2="-256" x1="0" />
            <line x2="192" y1="-160" y2="-160" x1="256" />
            <arc ex="112" ey="-208" sx="192" sy="-160" r="88" cx="116" cy="-120" />
            <line x2="48" y1="-208" y2="-208" x1="112" />
            <line x2="48" y1="-112" y2="-112" x1="112" />
            <line x2="48" y1="-256" y2="-208" x1="48" />
            <line x2="48" y1="-64" y2="-112" x1="48" />
            <arc ex="48" ey="-208" sx="48" sy="-112" r="56" cx="16" cy="-160" />
            <arc ex="192" ey="-160" sx="112" sy="-112" r="88" cx="116" cy="-200" />
        </blockdef>
        <block symbolname="and4" name="XLXI_5">
            <blockpin signalname="r" name="I0" />
            <blockpin signalname="XLXN_11" name="I1" />
            <blockpin signalname="XLXN_10" name="I2" />
            <blockpin signalname="XLXN_8" name="I3" />
            <blockpin signalname="XLXN_24" name="O" />
        </block>
        <block symbolname="and4" name="XLXI_6">
            <blockpin signalname="XLXN_12" name="I0" />
            <blockpin signalname="b" name="I1" />
            <blockpin signalname="a" name="I2" />
            <blockpin signalname="XLXN_9" name="I3" />
            <blockpin signalname="XLXN_25" name="O" />
        </block>
        <block symbolname="and4" name="XLXI_7">
            <blockpin signalname="r" name="I0" />
            <blockpin signalname="b" name="I1" />
            <blockpin signalname="XLXN_7" name="I2" />
            <blockpin signalname="op" name="I3" />
            <blockpin signalname="XLXN_26" name="O" />
        </block>
        <block symbolname="and4" name="XLXI_8">
            <blockpin signalname="XLXN_6" name="I0" />
            <blockpin signalname="XLXN_5" name="I1" />
            <blockpin signalname="a" name="I2" />
            <blockpin signalname="op" name="I3" />
            <blockpin signalname="XLXN_27" name="O" />
        </block>
        <block symbolname="inv" name="XLXI_9">
            <blockpin signalname="b" name="I" />
            <blockpin signalname="XLXN_5" name="O" />
        </block>
        <block symbolname="inv" name="XLXI_10">
            <blockpin signalname="r" name="I" />
            <blockpin signalname="XLXN_6" name="O" />
        </block>
        <block symbolname="inv" name="XLXI_11">
            <blockpin signalname="a" name="I" />
            <blockpin signalname="XLXN_7" name="O" />
        </block>
        <block symbolname="inv" name="XLXI_12">
            <blockpin signalname="op" name="I" />
            <blockpin signalname="XLXN_8" name="O" />
        </block>
        <block symbolname="inv" name="XLXI_13">
            <blockpin signalname="op" name="I" />
            <blockpin signalname="XLXN_9" name="O" />
        </block>
        <block symbolname="inv" name="XLXI_14">
            <blockpin signalname="a" name="I" />
            <blockpin signalname="XLXN_10" name="O" />
        </block>
        <block symbolname="inv" name="XLXI_15">
            <blockpin signalname="b" name="I" />
            <blockpin signalname="XLXN_11" name="O" />
        </block>
        <block symbolname="inv" name="XLXI_16">
            <blockpin signalname="r" name="I" />
            <blockpin signalname="XLXN_12" name="O" />
        </block>
        <block symbolname="or4" name="XLXI_17">
            <blockpin signalname="XLXN_27" name="I0" />
            <blockpin signalname="XLXN_26" name="I1" />
            <blockpin signalname="XLXN_25" name="I2" />
            <blockpin signalname="XLXN_24" name="I3" />
            <blockpin signalname="overflow" name="O" />
        </block>
    </netlist>
    <sheet sheetnum="1" width="3520" height="2720">
        <branch name="op">
            <wire x2="576" y1="512" y2="512" x1="352" />
            <wire x2="576" y1="512" y2="528" x1="576" />
            <wire x2="848" y1="528" y2="528" x1="576" />
            <wire x2="576" y1="528" y2="592" x1="576" />
            <wire x2="656" y1="592" y2="592" x1="576" />
            <wire x2="656" y1="592" y2="736" x1="656" />
            <wire x2="688" y1="736" y2="736" x1="656" />
            <wire x2="1136" y1="736" y2="736" x1="688" />
            <wire x2="688" y1="736" y2="1072" x1="688" />
            <wire x2="784" y1="1072" y2="1072" x1="688" />
            <wire x2="1392" y1="1072" y2="1072" x1="784" />
            <wire x2="784" y1="1072" y2="1392" x1="784" />
            <wire x2="1392" y1="1392" y2="1392" x1="784" />
            <wire x2="848" y1="384" y2="528" x1="848" />
            <wire x2="1136" y1="384" y2="384" x1="848" />
        </branch>
        <branch name="a">
            <wire x2="576" y1="736" y2="736" x1="352" />
            <wire x2="640" y1="736" y2="736" x1="576" />
            <wire x2="640" y1="736" y2="800" x1="640" />
            <wire x2="1392" y1="800" y2="800" x1="640" />
            <wire x2="640" y1="800" y2="1136" x1="640" />
            <wire x2="1136" y1="1136" y2="1136" x1="640" />
            <wire x2="640" y1="1136" y2="1456" x1="640" />
            <wire x2="1392" y1="1456" y2="1456" x1="640" />
            <wire x2="640" y1="448" y2="736" x1="640" />
            <wire x2="1136" y1="448" y2="448" x1="640" />
        </branch>
        <branch name="b">
            <wire x2="560" y1="928" y2="928" x1="352" />
            <wire x2="592" y1="928" y2="928" x1="560" />
            <wire x2="720" y1="928" y2="928" x1="592" />
            <wire x2="592" y1="928" y2="1200" x1="592" />
            <wire x2="1392" y1="1200" y2="1200" x1="592" />
            <wire x2="592" y1="1200" y2="1520" x1="592" />
            <wire x2="1136" y1="1520" y2="1520" x1="592" />
            <wire x2="1392" y1="864" y2="864" x1="592" />
            <wire x2="592" y1="864" y2="928" x1="592" />
            <wire x2="720" y1="512" y2="928" x1="720" />
            <wire x2="880" y1="512" y2="512" x1="720" />
        </branch>
        <branch name="r">
            <wire x2="512" y1="1120" y2="1120" x1="352" />
            <wire x2="576" y1="1120" y2="1120" x1="512" />
            <wire x2="576" y1="1120" y2="1584" x1="576" />
            <wire x2="880" y1="1584" y2="1584" x1="576" />
            <wire x2="512" y1="1040" y2="1120" x1="512" />
            <wire x2="768" y1="1040" y2="1040" x1="512" />
            <wire x2="768" y1="928" y2="1040" x1="768" />
            <wire x2="864" y1="928" y2="928" x1="768" />
            <wire x2="912" y1="928" y2="928" x1="864" />
            <wire x2="1136" y1="928" y2="928" x1="912" />
            <wire x2="912" y1="928" y2="1264" x1="912" />
            <wire x2="1392" y1="1264" y2="1264" x1="912" />
            <wire x2="1392" y1="576" y2="576" x1="864" />
            <wire x2="864" y1="576" y2="928" x1="864" />
        </branch>
        <iomarker fontsize="28" x="352" y="512" name="op" orien="R180" />
        <iomarker fontsize="28" x="352" y="736" name="a" orien="R180" />
        <iomarker fontsize="28" x="352" y="928" name="b" orien="R180" />
        <iomarker fontsize="28" x="352" y="1120" name="r" orien="R180" />
        <instance x="1392" y="640" name="XLXI_5" orien="R0" />
        <instance x="1392" y="992" name="XLXI_6" orien="R0" />
        <instance x="1392" y="1328" name="XLXI_7" orien="R0" />
        <instance x="1392" y="1648" name="XLXI_8" orien="R0" />
        <branch name="XLXN_5">
            <wire x2="1392" y1="1520" y2="1520" x1="1360" />
        </branch>
        <instance x="1136" y="1552" name="XLXI_9" orien="R0" />
        <instance x="880" y="1616" name="XLXI_10" orien="R0" />
        <branch name="XLXN_6">
            <wire x2="1392" y1="1584" y2="1584" x1="1104" />
        </branch>
        <branch name="XLXN_7">
            <wire x2="1392" y1="1136" y2="1136" x1="1360" />
        </branch>
        <instance x="1136" y="1168" name="XLXI_11" orien="R0" />
        <branch name="XLXN_8">
            <wire x2="1392" y1="384" y2="384" x1="1360" />
        </branch>
        <instance x="1136" y="416" name="XLXI_12" orien="R0" />
        <branch name="XLXN_9">
            <wire x2="1392" y1="736" y2="736" x1="1360" />
        </branch>
        <instance x="1136" y="768" name="XLXI_13" orien="R0" />
        <branch name="XLXN_10">
            <wire x2="1392" y1="448" y2="448" x1="1360" />
        </branch>
        <instance x="1136" y="480" name="XLXI_14" orien="R0" />
        <instance x="880" y="544" name="XLXI_15" orien="R0" />
        <branch name="XLXN_11">
            <wire x2="1392" y1="512" y2="512" x1="1104" />
        </branch>
        <branch name="XLXN_12">
            <wire x2="1392" y1="928" y2="928" x1="1360" />
        </branch>
        <instance x="1136" y="960" name="XLXI_16" orien="R0" />
        <instance x="2096" y="1088" name="XLXI_17" orien="R0" />
        <branch name="XLXN_24">
            <wire x2="2096" y1="480" y2="480" x1="1648" />
            <wire x2="2096" y1="480" y2="832" x1="2096" />
        </branch>
        <branch name="XLXN_25">
            <wire x2="1872" y1="832" y2="832" x1="1648" />
            <wire x2="1872" y1="832" y2="896" x1="1872" />
            <wire x2="2096" y1="896" y2="896" x1="1872" />
        </branch>
        <branch name="XLXN_26">
            <wire x2="1872" y1="1168" y2="1168" x1="1648" />
            <wire x2="1872" y1="960" y2="1168" x1="1872" />
            <wire x2="2096" y1="960" y2="960" x1="1872" />
        </branch>
        <branch name="XLXN_27">
            <wire x2="2096" y1="1488" y2="1488" x1="1648" />
            <wire x2="2096" y1="1024" y2="1488" x1="2096" />
        </branch>
        <branch name="overflow">
            <wire x2="2384" y1="928" y2="928" x1="2352" />
        </branch>
        <iomarker fontsize="28" x="2384" y="928" name="overflow" orien="R0" />
    </sheet>
</drawing>