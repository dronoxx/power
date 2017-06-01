import RPi.GPIO as GPIO
import sys

GPIO.setwarnings(False)

DEVICE_PIN=16

GPIO.setmode(GPIO.BCM)
GPIO.setup(DEVICE_PIN, GPIO.OUT)

OPTIONS = {
    'HIGH': GPIO.HIGH,
    'LOW': GPIO.LOW
}

if len(sys.argv) > 1:
    try:
    	GPIO.output(DEVICE_PIN,OPTIONS[sys.argv[1]])
    except KeyError,e:
        pass
        
