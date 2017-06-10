import RPi.GPIO as GPIO
import sys

GPIO.setwarnings(False)
GPIO.setmode(GPIO.BCM)

OPTIONS = {
    'ON': GPIO.LOW,
    'OFF': GPIO.HIGH
}

if len(sys.argv) > 2:
    try:
        DEVICE_PIN = int(sys.argv[1])
        GPIO.setup(DEVICE_PIN, GPIO.OUT)
        GPIO.output(DEVICE_PIN, OPTIONS[sys.argv[2]])
    except Exception, e:
        pass
