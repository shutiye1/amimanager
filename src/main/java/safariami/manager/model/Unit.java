package safariami.manager.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@Entity
@Table(name = "unit")
public class Unit {
    @Id
    private int code;
    private String unit;
    private String name;
    @Column(nullable = true, name="unitName")
    private String unitName;
    @Column(nullable = true)
    private String quantity;
    @Column(nullable = true)
    private String divination;
    // code, unit		// Quantity			Unit name		SI definition (comment)


    public Unit() {
    }

    public Unit(int code, String unit, String quantity, String unitName, String divination) {
        this.code = code;
        this.unit = unit;
        this.quantity = quantity;
        this.unitName = unitName;
        this.name = getName(code);
        this.divination = divination;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getDivination() {
        return divination;
    }

    public void setDivination(String divination) {
        this.divination = divination;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void aVoid() {
        Map<Integer, Unit> map = new HashMap<Integer, Unit>();
        map.put(1, new Unit(1, "a", "time", "year", "52*7*24*60*60 s"));
        map.put(2, new Unit(2, "mo", "time","month","31*24*60*60 s"));
        map.put(3, new Unit(3, "wk", "time","week","7*24*6*60 s"));
        map.put(4, new Unit(4, "d", "time","day","24*60*60 s"));
        map.put(5, new Unit(5, "h", "time	","hour","60*60 s"));
        map.put(6, new Unit(6, "min.", "time","min","60 s"));
        map.put(7, new Unit(7, "s", "time (t)","second","s"));
        map.put(8, new Unit(8, "°", "(phase) angle","degree","rad*180/π" ));
        map.put(9, new Unit(9, "°C", "temperature (T)	","degree celsius","	K-273.15"));
        map.put(10, new Unit(10, "currency", "(local) currency",null,null));
        map.put(11, new Unit(11, "m", "length (l)","metre","m"));
        map.put(12, new Unit(12, "m/s", "speed (v)","metre per second","m/s"));
        map.put(13, new Unit(13, "m³", "volume (V)","cubic metre","m³"));
        map.put(14, new Unit(14, "m³", "corrected volume","cubic metre","m³"));
        map.put(15, new Unit(15, "m³/h", "volume flux","cubic metre per hour","m³/(60*60s)"));
        map.put(16, new Unit(16, "m³/h", "corrected volume flux","cubic metre per hour","m³/(60*60s)"));
        map.put(17, new Unit(17, "m³/d", "volume flux",null,"m³/(24*60*60s)"));
        map.put(18, new Unit(18, "m³/d", "corrected volume flux",null,"	m³/(24*60*60s)"));
        map.put(19, new Unit(19, "l", "volume","litre","10-3 m³"));
        map.put(20, new Unit(20, "kg", "mass (m)","kilogram", "1000g"));
        map.put(21, new Unit(21, "N", "force (F)","newton", "N"));
        map.put(22, new Unit(22, "Nm", "energy","newtonmeter","J = Nm = Ws"));
        map.put(23, new Unit(23, "Pa", "pressure (p)","pascal","N/m²"));
        map.put(24, new Unit(24, "bar", "pressure (p)","bar","10⁵ N/m²"));
        map.put(25, new Unit(25, "J", "energy","joule","J = Nm = Ws"));
        map.put(26, new Unit(26, "J/h", "thermal power","joule per hour","J/(60*60s)"));
        map.put(27, new Unit(27, "W", "active power (P)","watt","W = J/s"));
        map.put(28, new Unit(28, "VA", "apparent power (S)","volt-ampere",null));
        map.put(29, new Unit(29, "var", "reactive power (Q)","var",null));
        map.put(30, new Unit(30, "Wh", "active energy","watt-hour","W*(60*60s)"));
        map.put(31, new Unit(31, "VAh", "apparent energy","volt-ampere-hour","VA*(60*60s)"));
        map.put(32, new Unit(32, "varh", "reactive energy","var-hour","var*(60*60s)"));
        map.put(33, new Unit(33, "A", "current (I)","ampere","A"));
        map.put(34, new Unit(34, "C", "electrical charge (Q)","coulomb","C = As"));
        map.put(35, new Unit(35, "V", "voltage (U)","volt","V"));
        map.put(36, new Unit(36, "V/m", "electr. field strength (E)","volt per metre",null));
        map.put(37, new Unit(37, "F", "capacitance (C)","farad","C/V = As/V"));
        map.put(38, new Unit(38, "Ω", "resistance (R)","ohm","Ω = V/A"));
        map.put(39, new Unit(39, "Ωm²/m", "resistivity (ρ)","Ωm",null));
        map.put(40, new Unit(40, "Wb", "magnetic flux (Φ)","weber","Wb = Vs"));
        map.put(41, new Unit(41, "T", "magnetic flux density (B)	","tesla","Wb/m2"));
        map.put(42, new Unit(42, "A/m", "magnetic field strength (H)","ampere per metre","A/m"));
        map.put(43, new Unit(43, "H", "inductance (L)","henry","H = Wb/A"));
        map.put(44, new Unit(44, "Hz", "frequency (f, ω)","hertz","1/s"));
        map.put(46, new Unit(46, "1/(varh)", "R_B","reactive energy meter constant or pulse value",null));
        map.put(47, new Unit(47, "1/(VAh)", "R_S","apparent energy meter constant or pulse value",null));
        map.put(48, new Unit(48, "V²h", "volt-squared hour","volt-squaredhours","V²(60*60s)"));
        map.put(49, new Unit(49, "A²h", "ampere-squared hour","ampere-squaredhours","A²(60*60s)"));
        map.put(50, new Unit(50, "kg/s", "mass flux","kilogram per second","kg/s"));
        map.put(51, new Unit(51, "S, mho", "conductance siemens","","1/Ω"));
        map.put(52, new Unit(52, "K", "temperature (T)","kelvin",null));
        map.put(53, new Unit(53, "1/(V²h)", "R_U²h","Volt-squared hour meter constant or pulse value",null));
        map.put(54, new Unit(54, "1/(A²h)", "R_I²h","Ampere-squared hour meter constant or pulse value",null));
        map.put(55, new Unit(55, "1/m³", "R_V","meter constant or pulse value (volume)", null));
        map.put(56, new Unit(56, "%", "percentage","%",null));
        map.put(57, new Unit(57, "Ah", "ampere-hours","ampere-hour",null));
        map.put(58, new Unit(60, "Wh/m³", "energy per volume","","3,6*103 J/m³"));
        map.put(59, new Unit(61, "J/m³", "calorific value","wobbe", null));
        map.put(60, new Unit(62, "Mol %", "molar fraction of","mole percent","(Basic gas composition unit)  gas composition  "));
        map.put(61, new Unit(63, "g/m³", "mass density, quantity of material","Gas analysis, accompanying elements",null));
        map.put(62, new Unit(64, "Pa s", "dynamic viscosity pascal second","Characteristic of gas stream",null));
        map.put(253, new Unit(253, "reserved", "reserved",null,null));
        map.put(254, new Unit(254, "other", "other unit",null,null));
    }

    public String getName(int intValue) {
        String str;
        switch (intValue) {
            case 0: // NONE
                str = "None";
                break;
            case 1: // YEAR
                str = "Year";
                break;
            case 2: // MONTH
                str = "Month";
                break;
            case 3: // WEEK
                str = "Week";
                break;
            case 4: // DAY
                str = "Day";
                break;
            case 5: // HOUR
                str = "Hour";
                break;
            case 6: // MINUTE
                str = "Minute";
                break;
            case 7: // SECOND
                str = "Second";
                break;
            case 8: // PHASE_ANGLEGEGREE
                str = "PhaseAngle";
                break;
            case 9: // TEMPERATURE
                str = "Temperature";
                break;
            case 10: // LOCAL_CURRENCY
                str = "LocalCurrency";
                break;
            case 11: // LENGTH
                str = "Length";
                break;
            case 12: // SPEED
                str = "Speed";
                break;
            case 13: // VOLUME_CUBIC_METER
                str = "VolumeCubicMeter";
                break;
            case 14: // CORRECTED_VOLUME
                str = "CorrectededCVolume";
                break;
            case 15: // VOLUME_FLUX_HOUR
                str = "VolumeFluxHour";
                break;
            case 16: // CORRECTED_VOLUME_FLUX_HOUR
                str = "CorrectedVolumeFluxHour";
                break;
            case 17: // VOLUME_FLUXDAY
                str = "VolumeFluxDay";
                break;
            case 18: // CORRECTE_VOLUME_FLUX_DAY
                str = "CorrectedVolumeFluxDay";
                break;
            case 19: // VOLUME_LITER
                str = "VolumeLiter";
                break;
            case 20: // MASS_KG
                str = "MassKg";
                break;
            case 21: // FORCE
                str = "Force";
                break;
            case 22: // ENERGY
                str = "Energy";
                break;
            case 23: // PRESSURE_PASCAL
                str = "PressurePascal";
                break;
            case 24: // PRESSURE_BAR
                str = "PressureBar";
                break;
            case 25: // ENERGY_JOULE
                str = "EnergyJoule";
                break;
            case 26: // THERMAL_POWER
                str = "ThermalPower";
                break;
            case 27: // ACTIVE_POWER
                str = "ActivePower";
                break;
            case 28: // APPARENT_POWER
                str = "ApparentPower";
                break;
            case 29: // REACTIVE_POWER
                str = "ReactivePower";
                break;
            case 30: // ACTIVE_ENERGY
                str = "ActiveEnergy";
                break;
            case 31: // APPARENT_ENERGY
                str = "ApparentEenergy";
                break;
            case 32: // REACTIVE_ENERGY
                str = "ReactiveEnergy";
                break;
            case 33: // CURRENT
                str = "Current";
                break;
            case 34: // ELECTRICAL_CHARGE
                str = "ElectricalCharge";
                break;
            case 35: // VOLTAGE
                str = "Voltage";
                break;
            case 36: // ELECTRICAL_FIELD_STRENGTH
                str = "ElectricalFieldStrength";
                break;
            case 37: // CAPACITY
                str = "Capacity";
                break;
            case 38: // RESISTANCE
                str = "Resistance";
                break;
            case 39: // RESISTIVITY
                str = "Resistivity";
                break;
            case 40: // MAGNETIC_FLUX
                str = "MagneticFlux";
                break;
            case 41: // INDUCTION
                str = "Induction";
                break;
            case 42: // MAGNETIC
                str = "Magnetic";
                break;
            case 43: // INDUCTIVITY
                str = "Inductivity";
                break;
            case 44: // FREQUENCY
                str = "Frequency";
                break;
            case 45: // ACTIVE
                str = "Active";
                break;
            case 46: // REACTIVE
                str = "Reactive";
                break;
            case 47: // APPARENT
                str = "Apparent";
                break;
            case 48: // V260
                str = "V260";
                break;
            case 49: // A260
                str = "A260";
                break;
            case 50: // MASS_KG_PER_SECOND
                str = "MassKgPerSecond";
                break;
            case 51: // CONDUCTANCE
                str = "Conductance";
                break;
            case 52: // KELVIN
                str = "Kelvin.";
                break;
            case 53: // V2H
                str = "V2H";
                break;
            case 54: // A2H
                str = "A2H";
                break;
            case 55: // CUBIC_METER_RV
                str = "CubicMeterRV";
                break;
            case 56: // PERCENTAGE
                str = "Percentage";
                break;
            case 57: // AMPERE_HOURS
                str = "AmpereHours";
                break;
            case 60: // ENERGY_PER_VOLUME
                str = "EnergyPerVolume";
                break;
            case 61: // WOBBE
                str = "WOBBE";
                break;
            case 62: // MOLE_PERCENT
                str = "MolePercent";
                break;
            case 63: // MASS_DENSITY
                str = "MassDensity";
                break;
            case 64: // PASCAL_SECOND
                str = "PascalSecond";
                break;
            case 65: // JOULE_KILOGRAM
                str = "JouleKilogram.";
                break;
            case 70: // SIGNAL_STRENGTH
                str = "SignalStrength";
                break;
            case 254: // OTHER_UNIT
                str = "OtherUnit";
                break;
            case 255: // NO_UNIT
                str = "NoUnit";
                break;
            default:
                str = "Unknown :" + String.valueOf(intValue);
        }
        return str;
    }

}
