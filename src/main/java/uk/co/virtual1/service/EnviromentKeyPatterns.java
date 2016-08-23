package uk.co.virtual1.service;

import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * @author Mikhail Tkachenko created on 10.06.2016
 */
interface EnviromentKeyPatterns {
    Pattern BOOLEAN = compile("(true)|(false)");
    Pattern NUMBER = compile("[\\d]+");
    Pattern NUMBER_OPTIONAL = compile("[\\d]*");
    Pattern STRING = compile(".+");
}
