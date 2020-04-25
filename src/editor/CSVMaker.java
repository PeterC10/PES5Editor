package editor;

import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class CSVMaker
{
  private static String[] team;
  private static char separator = ',';
  private static String attributeNotSupported = "???";
  private OptionFile of;
  private Stats stats;
  
  public boolean makeFile(OptionFile paramOptionFile, Stats paramStats, File paramFile, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    this.of = paramOptionFile;
    this.stats = paramStats;
    boolean bool = false;
    try
    {
      FileOutputStream fs = new FileOutputStream(paramFile);
			OutputStreamWriter ow = new OutputStreamWriter(fs, "UTF8");
			ow.write('\uFEFF');
			BufferedWriter out = new BufferedWriter(ow);
      int i = 5000;
      team = Clubs.getNames(this.of);
      if (paramBoolean1)
      {
        writeHeadings(out);
        out.write(13);
        out.flush();
        out.write(10);
        out.flush();
      }
      for (int j = 1; j < 4896; j++) {
        writePlayer(out, j);
      }
      if (paramBoolean2) {
        for (int j = 4896; j < i; j++) {
          writePlayer(out, j);
        }
      }
      if (paramBoolean3) {
        for (int j = 32768; j < 32952; j++) {
          writePlayer(out, j);
        }
      }
      out.close();
      bool = true;
    }
    catch (IOException localIOException)
    {
      bool = false;
    }
    return bool;
  }
  
  private void writeName(int paramInt, BufferedWriter out)
    throws IOException
  {
    Player localPlayer = new Player(this.of, paramInt, 0);
    String str = localPlayer.name.replaceAll(",", "");
    out.write(str);
    out.flush();
  }

  private void writeShirtName(int paramInt, BufferedWriter out)
          throws IOException
  {
    Player localPlayer = new Player(this.of, paramInt, 0);
    String str = localPlayer.getShirtName(paramInt);
    out.write(str);
    out.flush();
  }
  
  private void writeHeadings(BufferedWriter out)
    throws IOException
  {
    String[] head = {
        //Player Name
        "NAME", "SHIRT NAME",
        //Basic Settings
        "AGE", "INJURY TOLERANCE", "DRIBBLE STYLE", "FK STYLE", "PK STYLE", "DK STYLE",
        "GOAL CELEBRATION 1", "GOAL CELEBRATION 2", "GROWTH TYPE",
        //Basic Settings - Hidden
        "SPECIFIC GROWTH TYPE", "CONSISTENCY",
        //Position
        "STRONG FOOT", "FAVOURED SIDE", "REGISTERED POSITION",
        "GK", "SW", "CB", "SB", "DM", "WB", "CM", "SM", "AM", "WF", "SS", "CF",
        //Club & Nationality
        "NATIONALITY", "INTERNATIONAL NUMBER", "CLASSIC NUMBER", "CLUB TEAM", "CLUB NUMBER",
        //Appearance - Head - Face
        "FACE TYPE", "SKIN COLOR", "HEAD HEIGHT", "HEAD WIDTH", "HEAD POSITION", "PRESET FACE NUMBER",
        //Appearance - Head - Face - Brows
        "BROWS TYPE", "BROWS ANGLE", "BROWS HEIGHT", "EYEBROW SPACING",
        //Appearance - Head - Face - Eyes
        "EYES TYPE", "EYES POSITION", "EYES ANGLE", "EYES LENGTH", "EYES WIDTH",
        "EYE COLOR 1", "EYE COLOR 2",
        //Appearance - Head - Face - Nose
        "NOSE TYPE", "NOSE HEIGHT", "NOSE WIDTH",
        //Appearance - Head - Face - Cheeks
        "CHEEK TYPE", "CHEEK SHAPE",
        //Appearance - Head - Face - Mouth
        "MOUTH TYPE", "MOUTH SIZE", "MOUTH POSITION",
        //Appearance - Head - Face - Jaw
        "JAW TYPE", "CHIN HEIGHT", "CHIN WIDTH",
        //Appearance - Head - Hair - Hairstyle
        "HAIR TYPE", "HAIR SHAPE", "HAIR FRONT", "HAIR VOLUME", "HAIR DARKNESS",
        //Appearance - Head - Hair - Hairstyle - Color
        "HAIR COLOR TYPE", "HAIR COLOR PATTERN", "HAIR COLOR RGB-R", "HAIR COLOR RGB-G", "HAIR COLOR RGB-B",
        //Appearance - Head - Hair - Hairstyle - Bandana
        "BANDANA TYPE", "BANDANA COLOR",
        //Appearance - Head - Hair - Hairstyle - Cap
        "CAP", "CAP TYPE",
        //Appearance - Head - Hair - Facial Hair
        "FACIAL HAIR TYPE", "FACIAL HAIR COLOR",
        //Appearance - Head - Hair - Glasses
        "GLASSES TYPE", "GLASSES COLOR",
        //Physique
        "HEIGHT", "WEIGHT",
        "NECK LENGTH", "NECK WIDTH", "SHOULDER HEIGHT", "SHOULDER WIDTH",
        "CHEST MEASUREMENT", "WAIST CIRCUMFERENCE", "ARM CIRCUMFERENCE",
        "LEG CIRCUMFERENCE", "CALF CIRCUMFERENCE", "LEG LENGTH",
        //Accessories
        "NECK WARMER", "NECKLACE TYPE", "NECKLACE COLOR", "WRISTBAND", "WRISTBAND COLOR",
        "BRACELET TYPE", "BRACELET COLOR", "GLOVES", "FINGER BAND TYPE",
        "SHIRT TYPE",  "SLEEVE LENGTH", "UNDER SHORTS", "UNDER SHORTS COLOR",
        "SOCKS TYPE", "ANKLE TAPE",
        //Ability - Standard
        "ATTACK", "DEFENSE", "BALANCE", "STAMINA", "TOP SPEED",
        "ACCELERATION", "RESPONSE", "AGILITY", "DRIBBLE ACCURACY",
        "DRIBBLE SPEED", "SHORT PASS ACCURACY", "SHORT PASS SPEED",
        "LONG PASS ACCURACY", "LONG PASS SPEED", "SHOT ACCURACY",
        "SHOT POWER", "SHOT TECHNIQUE", "FREE KICK ACCURACY",
        "SWERVE", "HEADING", "JUMP", "TECHNIQUE", "AGGRESSION",
        "MENTALITY", "GOALKEEPING", "TEAMWORK", "CONDITION",
        "WEAK FOOT ACCURACY", "WEAK FOOT FREQUENCY",
        //Ability - Special
        "DRIBBLING", "TACTICAL DRIBBLE", "POSITIONING", "REACTION",
        "PLAYMAKING", "PASSING", "SCORING", "1-1 SCORING", "POST PLAYER",
        "LINES", "MIDDLE SHOOTING", "SIDE", "CENTRE", "PENALTIES",
        "1-TOUCH PASS", "OUTSIDE", "MARKING", "SLIDING", "COVERING",
        "D-LINE CONTROL", "PENALTY STOPPER", "1-ON-1 STOPPER", "LONG THROW"
      };
      out.write("ID");
      out.flush();
      for (int h = 0; h < head.length; h++) {
        out.write(separator);
        out.flush();
        out.write(head[h]);
        out.flush();
      }
  }
  
  private void writeInterStatus(int paramInt, BufferedWriter out)
    throws IOException
  {
    String str = "0";
    int i = this.stats.nation.getValue(paramInt);
    if (i < 57) {
      for (int n = 0; n < 23; n++)
      {
        int k = this.of.toInt(this.of.data[(664054 + 46 * i + n * 2)]);
        int m = this.of.toInt(this.of.data[(664055 + 46 * i + n * 2)]);
        if ((m << 8 | k) == paramInt)
        {
          int j = this.of.toInt(this.of.data[(657712 + 23 * i + n)]) + 1;
          str = String.valueOf(j);
        }
      }
    }
    out.write(str);
    out.flush();
  }
  
  private void writeClassicStatus(int paramInt, BufferedWriter out)
    throws IOException
  {
    String str = "0";
    int i = this.stats.nation.getValue(paramInt);
    int n = 0;
    if ((i == 6) || (i == 8) || (i == 9) || (i == 13) || (i == 15) || (i == 43) || (i == 44))
    {
      if (i == 43) {
        n = 57;
      }
      if (i == 44) {
        n = 58;
      }
      if (i == 6) {
        n = 59;
      }
      if (i == 8) {
        n = 60;
      }
      if (i == 9) {
        n = 61;
      }
      if (i == 13) {
        n = 62;
      }
      if (i == 15) {
        n = 63;
      }
      for (int i1 = 0; i1 < 23; i1++)
      {
        int k = this.of.toInt(this.of.data[(664054 + 46 * n + i1 * 2)]);
        int m = this.of.toInt(this.of.data[(664055 + 46 * n + i1 * 2)]);
        if ((m << 8 | k) == paramInt)
        {
          int j = this.of.toInt(this.of.data[(657712 + 23 * n + i1)]) + 1;
          str = String.valueOf(j);
        }
      }
    }
    out.write(str);
  }
  
  private void writeTeam(int paramInt, BufferedWriter out)
    throws IOException
  {
    String str1 = "0";
    String str2 = "";
    for (int m = 0; m < 138; m++) {
      for (int n = 0; n < 32; n++)
      {
        int j = this.of.toInt(this.of.data[(667458 + 64 * m + n * 2)]);
        int k = this.of.toInt(this.of.data[(667459 + 64 * m + n * 2)]);
        if ((k << 8 | j) == paramInt)
        {
          int i = this.of.toInt(this.of.data[(659414 + 32 * m + n)]) + 1;
          str2 = team[m];
          str1 = String.valueOf(i);
        }
      }
    }
    out.write(str2);
    out.flush();
    out.write(separator);
    out.flush();
    out.write(str1);
    out.flush();
  }
  
  private void writePlayer(BufferedWriter out, int paramInt)
    throws IOException
  {
    //Prepare Values
    int registeredPositionVal = Integer.parseInt(this.stats.regPos.getString(paramInt));
    String registeredPosition = CSVAttributes.getRegisteredPositionByValue().get(registeredPositionVal);

    //Write to CSV

    //ID
    out.write(Integer.toString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    //Name
    writeName(paramInt, out);
    out.flush();
    out.write(separator);
    out.flush();
    //Shirt Name
    writeShirtName(paramInt, out);
    out.flush();
    out.write(separator);
    out.flush();
    //Age
    out.write(this.stats.age.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    //Injury Tolerance
    out.write(this.stats.injury.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    //Dribble Style
    out.write(this.stats.dribSty.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    //FK Style
    out.write(this.stats.freekick.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    //PK Style
    out.write(this.stats.pkStyle.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    //DK Style
    out.write(this.stats.dkSty.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    //Goal Celebration 1
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Goal Celebration 2
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Growth Type
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Specific Growth Type
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Consistency
    out.write(this.stats.consistency.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    //Strong Foot
    out.write(this.stats.foot.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    //Favoured Side
    out.write(getSide(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    //Registered Position
    out.write(registeredPosition);
    out.flush();
    out.write(separator);
    out.flush();
    //All Positions
    out.write(this.stats.gk.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.cbwS.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.cbt.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.sb.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.dm.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.wb.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.cm.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.sm.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.om.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.wg.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.ss.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.cf.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    //Nationality
    out.write(this.stats.nation.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    //International Number
    writeInterStatus(paramInt, out);
    out.flush();
    out.write(separator);
    out.flush();
    //Classic Number
    writeClassicStatus(paramInt, out);
    out.flush();
    out.write(separator);
    out.flush();
    //Club Team
    writeTeam(paramInt, out);
    out.write(separator);
    out.flush();
    //Face Type
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Skin Color
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Head Height
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Head Width
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Head Position
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Preset Face Number
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Brows Type
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Brows Angle
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Brows Height
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Eyebrow Spacing
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Eyes Type
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Eyes Position
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Eyes Angle
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Eyes Length
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Eyes Width
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Eye Color 1
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Eye Color 2
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Nose Type
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Nose Height
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Nose Width
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Cheek Type
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Cheek Shape
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Mouth Type
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Mouth Size
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Mouth Position
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Jaw Type
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Chin Height
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Chin Width
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Hair Type
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Hair Shape
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Hair Front
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Hair Volume
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Hair Darkness
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Hair Color Type
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Hair Color Pattern
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Hair Color RGB-R
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Hair Color RGB-G
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Hair Color RGB-B
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Bandana Type
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Bandana Color
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Cap
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Cap Type
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Facial Hair Type
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Facial Hair Color
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Glasses Type
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Glasses Color
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Height
    out.write(this.stats.height.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    //Weight
    out.write(this.stats.weight.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    //Neck Length
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Neck Width
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Shoulder Height
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Shoulder Width
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Chest Measurement
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Waist Circumference
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Arm Circumference
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Leg Circumference
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Calf Circumference
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Leg Length
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Neck Warmer
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Necklace Type
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Necklace Color
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Wristband
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Wristband Color
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Bracelet Type
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Bracelet Color
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Gloves
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Finger Band Type
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Shirt Type
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Sleeve Length
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Under Shorts
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Under Shorts Color
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Socks Type
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Ankle Tape
    out.write(attributeNotSupported);
    out.flush();
    out.write(separator);
    out.flush();
    //Standard Abilities
    out.write(this.stats.attack.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.defence.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.balance.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.stamina.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.speed.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.accel.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.response.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.agility.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.dribAcc.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.dribSpe.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.sPassAcc.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.sPassSpe.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.lPassAcc.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.lPassSpe.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.shotAcc.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.shotPow.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.shotTec.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.fk.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.curling.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.heading.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.jump.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.tech.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.aggress.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.mental.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.gkAbil.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.team.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.condition.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.wfa.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.wff.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    //Special Abilities
    out.write(this.stats.drib.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.dribKeep.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.posit.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.offside.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.play.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.pass.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.scorer.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.k11.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.post.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.linePos.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.midShot.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.side.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.centre.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.pk.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.direct.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.outside.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.man.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.slide.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.cover.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.dLine.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.keeperPK.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.keeper11.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(this.stats.longThrow.getString(paramInt));
    out.flush();
    out.write(separator);
    out.flush();
    out.write(13);
    out.flush();
    out.write(10);
    out.flush();
  }
  
  private String getSide(int paramInt)
  {
    String str = "B";
    int i = this.stats.favSide.getValue(paramInt);
    if (i == 0) {
      str = this.stats.foot.getString(paramInt);
    }
    if (i == 1) {
      if (this.stats.foot.getValue(paramInt) == 0) {
        str = "L";
      } else {
        str = "R";
      }
    }
    return str;
  }
}


/* Location:              PESFan Editor 5.08.jar!/editor/CSVMaker.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */
