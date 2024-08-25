package com.modding_journeys.ender_pearl_staff;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import oshi.util.tuples.Pair;
import oshi.util.tuples.Triplet;

public class Constants {

	public static final String MOD_ID = "ender_pearl_staff";
	public static final String MOD_NAME = "Ender Pearl Staff";
	public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

	public static final boolean sendDebuggingLogs = true; // todo: include in configuration file, update from loaders

	public static void debug(String string) {
		if (sendDebuggingLogs) LOG.info(string);
	}
}
