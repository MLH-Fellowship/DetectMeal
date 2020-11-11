package com.project.detectmeal.objectanalyzer

import android.content.Context
import android.media.Image
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.common.model.LocalModel
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.objects.ObjectDetection
import com.google.mlkit.vision.objects.custom.CustomObjectDetectorOptions
import com.project.detectmeal.GraphicOverlay
import com.project.detectmeal.ObjectGraphic


class ObjectAnalyzer(
    ctx : Context,
    private val graphicOverlay: GraphicOverlay
) : ImageAnalysis.Analyzer {

    // set up local model builder
    private val localModel = LocalModel.Builder()
        .setAssetFilePath("lite-model_aiy_vision_classifier_food_V1_1.tflite")
        .build()

    //create option object to specify model parameters
    private val customObjectDetectorOptions = CustomObjectDetectorOptions.Builder(localModel)
        .setDetectorMode(CustomObjectDetectorOptions.STREAM_MODE)
        .enableClassification()
        .setClassificationConfidenceThreshold(0.8f)
        .setMaxPerObjectLabelCount(1)
        .build()

    var needUpdateGraphicOverlayImageSourceInfo = true

    // TODO: Instantiate ObjectRecognition detector
    private val objectDetector = ObjectDetection.getClient(customObjectDetectorOptions)


    @androidx.camera.core.ExperimentalGetImage
    override fun analyze(imageProxy: ImageProxy) {
        if (needUpdateGraphicOverlayImageSourceInfo) {
            val rotationDegrees = imageProxy.imageInfo.rotationDegrees
            if (rotationDegrees == 0 || rotationDegrees == 100) {
                graphicOverlay.setImageSourceInfo(imageProxy.width, imageProxy.height, false)
            } else {
                graphicOverlay.setImageSourceInfo(imageProxy.height, imageProxy.width, false)
            }
            needUpdateGraphicOverlayImageSourceInfo = false
        }
        val mediaImage : Image? = imageProxy.image
        if (mediaImage != null) {
            //create an input image object from media image specifying the appropriate rotation degree
            val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

            //use the object detector to process the image
            objectDetector.process(image)
                .addOnFailureListener { Log.d(TAG, it.printStackTrace().toString()) }
                .addOnSuccessListener {
                    graphicOverlay.clear()
                    for (detectedObject in it) {
                        graphicOverlay.add(ObjectGraphic(graphicOverlay, detectedObject))
                    }
                    graphicOverlay.postInvalidate()
                }.addOnCompleteListener {
                    imageProxy.close()
                }
        }
    }

    companion object {
        private const val TAG = "ObjectAnalyzer"
    }
}