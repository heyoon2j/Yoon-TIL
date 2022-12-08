# Advanced

* Hooking
* Auto Start/Retry
* Caching


## Hooking
```
terraform {
    before_hook "before_hook" {
        commands     = ["apply", "plan"]
        execute      = ["echo", "Running Terraform"]
    }

    after_hook "after_hook" {
        commands     = ["apply", "plan"]
        execute      = ["echo", "Finished running Terraform"]
        run_on_error = true
    }

    error_hook "import_resource" {
        commands  = ["apply"]
        execute   = ["echo", "Error Hook executed"]
        on_errors = [
        ".*",
        ]
    }
}
```
https://terragrunt.gruntwork.io/docs/features/hooks/
</br>


---
## Auto Start/Retry
https://terragrunt.gruntwork.io/docs/features/auto-retry/
</br>


---
## Caching

</br>



---
## 

</br>



---
## 

</br>



